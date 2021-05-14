package br.com.zupacademy.brunomeloesilva.proposta.biometria;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class Base64Validator implements ConstraintValidator<Base64, Object> {
	
	@Override
	public void initialize(Base64 annotation) {}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		return verificaSeStringRecebidaEstaEmBase64(value);
	}

	private boolean verificaSeStringRecebidaEstaEmBase64(Object value) {
		return value.toString().matches("^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$");
	}
}