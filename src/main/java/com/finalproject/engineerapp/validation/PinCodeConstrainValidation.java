package com.finalproject.engineerapp.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PinCodeConstrainValidation implements ConstraintValidator<PinCode, String> {

    private String pinPrefix;
    private int pinLength;

    @Override
    public void initialize(PinCode pinCode) {
        pinPrefix = pinCode.value();
        pinLength = pinCode.size();
    }

    @Override
    public boolean isValid(String pinCode, ConstraintValidatorContext constraintValidatorContext) {
        boolean result;

        if(pinCode != null) {
            boolean prefixResult = pinCode.startsWith(pinPrefix);
            boolean lengthResult = pinCode.length() == pinLength;
            result = prefixResult && lengthResult;
        } else {
            result = true;
        }

        return result;
    }
}
