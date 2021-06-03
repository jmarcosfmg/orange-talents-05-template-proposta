package br.com.zup.oragetalents.proposta.validators;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

@Component
public class UniqueValidator implements ConstraintValidator<Unique, String> {

	@PersistenceContext
	private EntityManager em;

	private String field;

	private Class<?> entity;

	public void initialize(Unique unique) {
		this.entity = unique.entity();
		this.field = unique.field();
	}

	public boolean isValid(String value, ConstraintValidatorContext context) {
		Long position = em.createQuery("Select count(c) from " + this.entity.getSimpleName() + " c where lower(c." + this.field
				+ ") like lower('" + value + "')", Long.class).getSingleResult();
		if(position.equals(0L)) return true;
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate("Já existe "+field+" com este valor.").addConstraintViolation();
		return (false);
	}
}
