package br.com.zupacademy.brunomeloesilva.proposta.validacoes;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = {ValorDuplicadoValidator.class})
@Retention(RUNTIME)
@Target(FIELD)
public @interface ValorDuplicado {
	
	String message() default "{br.com.zupacademy.brunomeloesilva.validacoesglobais.ValorDuplicado.message}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
	String nomeCampo();
	Class<?> classeDominio();
}
//TODO Se eu decobrir como pegar o nomeCampo aqui mesmo, por default, deixaria mais elegante.