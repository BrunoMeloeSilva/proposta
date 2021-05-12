package br.com.zupacademy.brunomeloesilva.proposta;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.brunomeloesilva.proposta.analisefinanceira.AnalisaRestricoesFinanceiras;
import br.com.zupacademy.brunomeloesilva.proposta.analisefinanceira.AnaliseFinanceiraDTORequest;
import br.com.zupacademy.brunomeloesilva.proposta.validacoes.PropostaJaExisteException;
import br.com.zupacademy.brunomeloesilva.proposta.validacoes.RegraDeNegocios;
import feign.FeignException.FeignClientException;
import feign.RetryableException;

@RestController
@RequestMapping("/propostas")
public class PropostaController {
	
	@PersistenceContext
	EntityManager entityManager;
	@Autowired
	Validator validator;
	@Autowired
	AnalisaRestricoesFinanceiras analisaRestricoesFinanceiras;
	
	@PostMapping
	@Transactional
	public ResponseEntity<PropostaDTOResponse> cadastrar(@RequestBody @Valid PropostaDTORequest propostaDTORequest
			,UriComponentsBuilder uriComponentsBuilder) throws Exception {
		
		verificaSeJaTemOCpfOuCnpjEmPropostaJaExiste(propostaDTORequest);
			
		PropostaModel propostaModel = propostaDTORequest.toModel();
		entityManager.persist(propostaModel);
		
		defineStatusDaProposta(propostaModel);
		
		URI uriProposta = uriComponentsBuilder.path("/propostas/{id}").build(propostaModel.getUUID());
		return ResponseEntity.created(uriProposta).body(new PropostaDTOResponse(propostaModel));
	}

	private void defineStatusDaProposta(PropostaModel propostaModel) throws RetryableException {
		try {
			checagemDaAnaliseFinanceira(propostaModel);
			propostaModel.setPropostaStatus(PropostaStatus.ELEGIVEL);
		}catch(FeignClientException ex) {
			propostaModel.setPropostaStatus(PropostaStatus.NAO_ELEGIVEL);
		}
	}

	private void checagemDaAnaliseFinanceira(PropostaModel propostaModel) throws FeignClientException, RetryableException {
		AnaliseFinanceiraDTORequest dtoRequest = new AnaliseFinanceiraDTORequest(propostaModel);
		analisaRestricoesFinanceiras.analisar(dtoRequest);
	}

	private void verificaSeJaTemOCpfOuCnpjEmPropostaJaExiste(PropostaDTORequest propostaDTORequest) throws PropostaJaExisteException, NoSuchMethodException, SecurityException {
		 SpringValidatorAdapter springValidatorAdapter = new SpringValidatorAdapter(validator);
		 BeanPropertyBindingResult beanPropertyBindingResult = new BeanPropertyBindingResult(propostaDTORequest, "propostaDTORequest");
		 springValidatorAdapter.validate(propostaDTORequest, beanPropertyBindingResult, RegraDeNegocios.class);
		 if (beanPropertyBindingResult.hasErrors()) {
			 MethodParameter methodParameter = new MethodParameter(this.getClass().getDeclaredMethod("cadastrar", PropostaDTORequest.class, UriComponentsBuilder.class), 0);
			 throw new PropostaJaExisteException(methodParameter, beanPropertyBindingResult);
		 }
	}
}
//TODO Falta descobrir a maneira de habilitar globalmente ofuscação no LogBack do Spring.