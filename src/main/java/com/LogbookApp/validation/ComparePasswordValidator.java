package com.LogbookApp.validation;

import com.LogbookApp.utility.MapperHelper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ComparePasswordValidator implements ConstraintValidator<ComparePassword, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        var password = MapperHelper.getStringField(value, "password");
        var passwordConfirmation = MapperHelper.getStringField(value, "confirmPassword");
        return password.equals(passwordConfirmation);
    }
}
