package br.com.zupacademy.brunomeloesilva.proposta.validacoes;

import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class PropostaJaExisteException extends MethodArgumentNotValidException{

	private static final long serialVersionUID = 1L;
	
	public PropostaJaExisteException(MethodParameter parameter, BindingResult bindingResult) {
		super(parameter, bindingResult);
	}
}
