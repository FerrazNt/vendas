package io.github.ferraznt.validation;

import io.github.ferraznt.validation.constraintvalidation.NotEmptyListValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NotEmptyListValidation.class)
public @interface NotEmptyList {

    String message() default "A Lista não pode ser vazia.";

    // Métodos Obrigatórios
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
