package br.com.zupacademy.brunomeloesilva.proposta.cartoes;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.brunomeloesilva.proposta.biometria.BiometriaDTORequest;
import br.com.zupacademy.brunomeloesilva.proposta.biometria.BiometriaModel;
import br.com.zupacademy.brunomeloesilva.proposta.cartoes.avisoviagem.AvisoViagemDTORequest;
import br.com.zupacademy.brunomeloesilva.proposta.cartoes.avisoviagem.AvisoViagemModel;
import br.com.zupacademy.brunomeloesilva.proposta.cartoes.bloqueio.BloqueioDTORequest;
import br.com.zupacademy.brunomeloesilva.proposta.cartoes.bloqueio.BloqueioModel;
import br.com.zupacademy.brunomeloesilva.proposta.cartoes.carteira.CarteiraDTORequest;
import br.com.zupacademy.brunomeloesilva.proposta.cartoes.carteira.CarteiraModel;
import feign.FeignException;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {
	
	@PersistenceContext
	EntityManager entityManager;
	@Autowired
	APICartao apiCartao;
	
	@PostMapping("/{numeroCartao}")
	@Transactional
	public ResponseEntity<Void> cadastrar(@PathVariable String numeroCartao
			, @RequestBody @Valid BiometriaDTORequest biometriaDTORequest
			,UriComponentsBuilder uriComponentsBuilder) {
		
		CartaoModel CartaoModel = entityManager.find(CartaoModel.class, numeroCartao);
		BiometriaModel biometriaModel = null;
		if(CartaoModel != null) {
			biometriaModel = biometriaDTORequest.toModel(CartaoModel);
			entityManager.persist(biometriaModel);
			URI uri = uriComponentsBuilder.path("/cartoes/{id}").build(biometriaModel.getId());
			return ResponseEntity.created(uri).build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PatchMapping("/{numeroCartao}")
	@Transactional
	public ResponseEntity<Void> bloquear(@PathVariable String numeroCartao
			, @RequestHeader("user-agent") String userAgent
			, @RequestHeader("host") String host) {
		
		CartaoModel cartaoModel = entityManager.find(CartaoModel.class, numeroCartao);
		if(cartaoModel == null)
			return ResponseEntity.notFound().build();
		
		if(cartaoModel.getBloqueio() != null)
			return ResponseEntity.unprocessableEntity().build();
			
		BloqueioModel bloqueioModel = new BloqueioModel(userAgent, host);
		entityManager.persist(bloqueioModel);
		cartaoModel.setBloqueio(bloqueioModel);
		
		//TODO O correto para resolver este ponto aqui, seria com publicação de eventos.
		try{
			CartaoStatusDTOResposnse informaBloqueioCartao = apiCartao.informaBloqueioCartao(numeroCartao, new BloqueioDTORequest());
			cartaoModel.setStatus(informaBloqueioCartao.getResultado());
		}catch (Exception e) {}

		return ResponseEntity.ok().build();		
	}
	
	@PostMapping("/{numeroCartao}/avisos-viagens")
	@Transactional
	public ResponseEntity<Void> avisoViagem(@PathVariable String numeroCartao
			, @RequestHeader("user-agent") String userAgent
			, @RequestHeader("host") String host
			, @RequestBody @Valid AvisoViagemDTORequest avisoViagemDTORequest) {
		
		CartaoModel cartaoModel = entityManager.find(CartaoModel.class, numeroCartao);
		if(cartaoModel == null)
			return ResponseEntity.notFound().build();
		
		//TODO O correto para resolver este ponto aqui, seria com publicação de eventos.
		try {
			apiCartao.informaAvisoViagem(numeroCartao, avisoViagemDTORequest);
			AvisoViagemModel avisoViagemModel = new AvisoViagemModel(avisoViagemDTORequest.getDestinoViagem()
					, avisoViagemDTORequest.getDataTerminoViagem(), userAgent, host, cartaoModel);
			entityManager.persist(avisoViagemModel);
		}catch (FeignException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro com o sistema legado de aviso de cartão.");
		}

		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/{numeroCartao}/carteiras-digitais")
	@Transactional
	public ResponseEntity<Void> incluirCarteiraDigital(@PathVariable String numeroCartao
			,@RequestBody @Valid CarteiraDTORequest carteiraDTORequest
			,UriComponentsBuilder uriComponentsBuilder){
		
		CartaoModel cartaoModel = entityManager.find(CartaoModel.class, numeroCartao);
		if(cartaoModel == null)
			return ResponseEntity.notFound().build();
		
		for (CarteiraModel carteiraDigital : cartaoModel.getCarteiras()) {
			if(carteiraDigital.getCarteira().equals(carteiraDTORequest.getCarteira()))
				throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Este cartão já foi vinculado ao " + carteiraDTORequest.getCarteira());
		}
		
		URI uri = null;
		try {
			//TODO O correto para resolver este ponto aqui, seria com publicação de eventos.
			apiCartao.vinculoComCarteiraDigital(numeroCartao, carteiraDTORequest);
			CarteiraModel carteiraModel = new CarteiraModel(cartaoModel, carteiraDTORequest.getEmail(), carteiraDTORequest.getCarteira());
			entityManager.persist(carteiraModel);
			uri = uriComponentsBuilder.path("/{numeroCartao}/carteiras-digitais/{IdCarteira}").build(numeroCartao, carteiraModel.getId());
		}catch (FeignException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro com o sistema legado de vinculo de carteiras.");
		}
		
		return ResponseEntity.created(uri).build();
	}
}
