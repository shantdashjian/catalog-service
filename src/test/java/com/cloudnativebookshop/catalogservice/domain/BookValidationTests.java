package com.cloudnativebookshop.catalogservice.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class BookValidationTests {
	private static Validator validator;

	@BeforeAll
	static void setUp() {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	@Test
	void whenAllFieldsCorrectThenValidationSucceeds() {
		var book = Book.of("1234567890", "Cloud Native Spring in Action", "Thomas Vitale", 19.99);
		Set<ConstraintViolation<Object>> violations = validator.validate(book);
		assertThat(violations).isEmpty();
	}

	@Test
	void whenIsbnDefinedButIncorrectThenValidationFails() {
		var book = Book.of("123456789", "Cloud Native Spring in Action", "Thomas Vitale", 19.99);
		Set<ConstraintViolation<Object>> violations = validator.validate(book);
		assertThat(violations).hasSize(1);
		assertThat(violations.iterator().next().getMessage())
			.isEqualTo("The ISBN format must be valid.");
	}
}
