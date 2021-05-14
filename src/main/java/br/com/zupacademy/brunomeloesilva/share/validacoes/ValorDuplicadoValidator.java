package br.com.zupacademy.brunomeloesilva.share.validacoes;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValorDuplicadoValidator implements ConstraintValidator<ValorDuplicado, Object> {

	private Class<?> classeDominio;
	private String nomeCampo;
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public void initialize(ValorDuplicado annotation) {
		this.nomeCampo = annotation.nomeCampo();
		this.classeDominio = annotation.classeDominio();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		String sql = String.format("Select count(*) > 0 From %s t Where t.%s = :%2$s", classeDominio.getSimpleName(), nomeCampo);
		
		TypedQuery<Boolean> query = entityManager.createQuery(sql, Boolean.class);
		query.setParameter(nomeCampo, value);
		
		Boolean isDuplicado = query.getSingleResult();
		
		return !isDuplicado;
	}
}
