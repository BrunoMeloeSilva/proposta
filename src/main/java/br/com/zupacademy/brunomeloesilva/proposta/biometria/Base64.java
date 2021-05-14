package br.com.zupacademy.brunomeloesilva.proposta.biometria;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = {Base64Validator.class})
@Retention(RUNTIME)
@Target(FIELD)
public @interface Base64 {
	
	String message() default "{br.com.zupacademy.brunomeloesilva.proposta.biometria.Base64.message}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}