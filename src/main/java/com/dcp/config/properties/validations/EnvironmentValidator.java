package com.dcp.config.properties.validations;

import com.dcp.config.properties.enums.EnvironmentName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnvironmentValidator implements ConstraintValidator<ValidEnvironment, String> {

	@Override
	public void initialize(ValidEnvironment constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		for (EnvironmentName env : EnvironmentName.values()) {
			if (env.getValue().equals(value)) {
				return true;
			}
		}

		return false;
	}
}
