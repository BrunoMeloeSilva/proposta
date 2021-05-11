package br.com.zupacademy.brunomeloesilva.proposta.validacoes;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

@Documented
@Constraint(validatedBy = {})
@Retention(RUNTIME)
@Target(FIELD)
@ConstraintComposition(CompositionType.OR)
@CPF
@CNPJ
public @interface CpfOuCnpj {
	//TODO Deveria sobrescrever as mensagens de @CPF e @CNPJ em uma única mensagem.
	String message() default "";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}