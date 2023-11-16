package com.finalproject.engineerapp.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PinCodeConstrainValidation.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PinCode {

    public String value() default "LT-";

    public String message() default "must start with LT-";

    public int size() default 8;

    //Groups can group related constraints
    public Class<?>[] groups() default {};

    //Payloads: provide custom details about validation failure (severity level, error code etc)
    public Class<? extends Payload>[] payload() default {};
}
