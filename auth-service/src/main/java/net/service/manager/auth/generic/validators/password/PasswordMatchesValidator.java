package net.service.manager.auth.generic.validators.password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import net.service.manager.auth.crud.dto.request.UserFormPasswordRequest;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, UserFormPasswordRequest> {

	@Override
    public boolean isValid(final UserFormPasswordRequest user, final ConstraintValidatorContext context) {
        return user.getNewPassword().equals(user.getMatchingPassword());
	}
}
