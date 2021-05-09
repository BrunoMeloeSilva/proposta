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
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.brunomeloesilva.validacoesglobais.PropostaJaExisteException;
import br.com.zupacademy.brunomeloesilva.validacoesglobais.RegraDeNegocios;

@RestController
@RequestMapping("/propostas")
public class PropostaController {
	
	@PersistenceContext
	EntityManager entityManager;
	@Autowired
	Validator validator;
	
	@PostMapping
	@Transactional
	public ResponseEntity<PropostaDTOResponse> cadastrar(@RequestBody @Valid PropostaDTORequest propostaDTORequest
			,UriComponentsBuilder uriComponentsBuilder) throws PropostaJaExisteException {
		
			try {
				validaAnotacoesDeRegrasDeNegocios(propostaDTORequest);
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			} catch (MethodArgumentNotValidException e) {
				throw new PropostaJaExisteException(e.getParameter(), e.getBindingResult());
			}
		
		PropostaModel propostaModel = propostaDTORequest.toModel();
		entityManager.persist(propostaModel);
		URI uriProposta = uriComponentsBuilder.path("/propostas/{id}").build(propostaModel.getUUID());
		return ResponseEntity.created(uriProposta).body(new PropostaDTOResponse(propostaModel));
	}

	private void validaAnotacoesDeRegrasDeNegocios(PropostaDTORequest propostaDTORequest) throws MethodArgumentNotValidException, NoSuchMethodException, SecurityException {
		 SpringValidatorAdapter springValidatorAdapter = new SpringValidatorAdapter(validator);
		 BeanPropertyBindingResult beanPropertyBindingResult = new BeanPropertyBindingResult(propostaDTORequest, "propostaDTORequest");
		 springValidatorAdapter.validate(propostaDTORequest, beanPropertyBindingResult, RegraDeNegocios.class);
		 if (beanPropertyBindingResult.hasErrors()) {
			 MethodParameter methodParameter = new MethodParameter(this.getClass().getDeclaredMethod("cadastrar", PropostaDTORequest.class, UriComponentsBuilder.class), 0);
			 throw new MethodArgumentNotValidException(methodParameter, beanPropertyBindingResult);
		 }
	}
}
//TODO Falta descobrir a maneira de habilitar globalmente ofuscação no LogBack do Spring.