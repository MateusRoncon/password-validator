package br.com.passwordValidator.service.impl.regex;

import br.com.passwordValidator.model.response.PasswordResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

import java.util.ArrayList;

import static br.com.passwordValidator.util.Messages.NO_UPPER_LETTERS_ERROR;

@RunWith(JUnitPlatform.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class RegexUpperLettersValidationTest {

    @Autowired
    RegexUpperLettersValidation validation = new RegexUpperLettersValidation();

    @Test
    public void givenAValidPassword_whenValidateAll_thenShouldPass() {

        String password = "1234a!B67";

        StepVerifier.create(validation.validate(password, new PasswordResponse()))
                .assertNext(response -> {
                    Assertions.assertNotNull(response);
                    Assertions.assertEquals(new ArrayList<>(), response.getErrorList());
                })
                .expectComplete()
                .log()
                .verify();

    }

    @Test
    public void givenAInvalidPassword_whenValidateAll_thenShouldAddToErrorList() {

        String password = "12345a!67";

        StepVerifier.create(validation.validate(password, new PasswordResponse()))
                .assertNext(response -> {
                    Assertions.assertNotNull(response);
                    Assertions.assertTrue(response.getErrorList().contains(NO_UPPER_LETTERS_ERROR));
                })
                .expectComplete()
                .log()
                .verify();
    }

}