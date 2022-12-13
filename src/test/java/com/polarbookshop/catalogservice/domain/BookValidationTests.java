package com.polarbookshop.catalogservice.domain;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BookValidationTests {

    private static Validator validator;

    @BeforeAll
    static void setup() {
        var validationFactory = Validation.buildDefaultValidatorFactory();
        validator = validationFactory.getValidator();
    }

    @Test
    void whenCreatingBookWithAllFieldsValidThenNoValidationErrors() {
        Book book =
                new Book("1234567891", "Cloud", "Vitale", 19.99);
        var errors = validator.validate(book);
        assertThat(errors).hasSize(0);
    }

    @Test
    void whenCreatingBookWithInvalidIsbnThenOneValidationErrorShouldComeBack() {
        Book book =
                new Book("123456789", "Cloud", "Vitale", 19.99);
        var errors = validator.validate(book);
        assertThat(errors).hasSize(1);
        assertThat(errors.iterator().next().getMessage())
                .isEqualTo("Book ISBN must be valid.");
    }
}
