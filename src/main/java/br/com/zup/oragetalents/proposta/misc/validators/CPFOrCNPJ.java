package br.com.zup.oragetalents.proposta.misc.validators;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

@CPF
@CNPJ
@ConstraintComposition(CompositionType.OR)
@Constraint(validatedBy = {})
@Target({ FIELD, PARAMETER })
@Retention(RUNTIME)
public @interface CPFOrCNPJ {

	String message()

	default "Documento inválido";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
