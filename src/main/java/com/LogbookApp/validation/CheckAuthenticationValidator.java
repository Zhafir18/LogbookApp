package com.LogbookApp.validation;

import com.LogbookApp.dto.account.ChangePasswordDTO;
import com.LogbookApp.service.AccountService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class CheckAuthenticationValidator implements ConstraintValidator<CheckAuthentication, ChangePasswordDTO> {
    @Autowired
    private AccountService accountService;

    @Override
    public boolean isValid(ChangePasswordDTO value, ConstraintValidatorContext context) {
        return accountService.checkUserPassword(value.getUsername(), value.getOldPassword());
    }
}
