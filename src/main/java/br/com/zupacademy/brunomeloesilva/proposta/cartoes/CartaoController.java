package br.com.zupacademy.brunomeloesilva.proposta.cartoes;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.brunomeloesilva.proposta.biometria.BiometriaDTORequest;
import br.com.zupacademy.brunomeloesilva.proposta.biometria.BiometriaModel;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {
	
	@PersistenceContext
	EntityManager entityManager;
	
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
}
