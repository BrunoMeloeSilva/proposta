package br.com.zupacademy.brunomeloesilva.proposta;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/propostas")
public class PropostaController {
	
	@PersistenceContext
	EntityManager entityManager;
	
	@PostMapping
	@Transactional
	public ResponseEntity<PropostaDTOResponse> Create(@RequestBody @Valid PropostaDTORequest propostaDTORequest
			,UriComponentsBuilder uriComponentsBuilder) {
		PropostaModel propostaModel = propostaDTORequest.toModel();
		entityManager.persist(propostaModel);
		URI uriProposta = uriComponentsBuilder.path("/propostas/{id}").build(propostaModel.getUUID());
		return ResponseEntity.created(uriProposta).body(new PropostaDTOResponse(propostaModel));
	}
}
//TODO Falta descobrir a maneira de habilitar globalmente ofuscação no LogBack do Spring.