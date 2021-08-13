package br.com.passwordValidator.service.impl.regex;

import br.com.passwordValidator.exception.PasswordCheckFailException;
import br.com.passwordValidator.model.response.PasswordResponse;
import br.com.passwordValidator.service.impl.PasswordCheckResponseHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

import java.util.ArrayList;

import static br.com.passwordValidator.util.Messages.NO_DIGIT_ERROR;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(JUnitPlatform.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class PasswordCheckResponseHandlerTest {

    @Autowired
    PasswordCheckResponseHandler handler = new PasswordCheckResponseHandler();

    PasswordResponse response = new PasswordResponse();

    @Test
    public void givenAValidPassword_whenValidateAll_thenShouldPass() {

        StepVerifier.create(handler.handleResponse(response))
                .assertNext(response -> {
                    Assertions.assertNotNull(response);
                    Assertions.assertEquals(new ArrayList<>(), response.getErrorList());
                })
                .expectComplete()
                .log()
                .verify();
    }

    @Test
    public void givenAInvalidPassword_whenValidateAll_thenShouldThrowsException() {

        response.getErrorList().add(NO_DIGIT_ERROR);

        StepVerifier.create(handler.handleResponse(response))
                .expectErrorSatisfies(e -> {
                    assertTrue(e instanceof PasswordCheckFailException);
                    var ex = (PasswordCheckFailException) e;
                    Assertions.assertEquals("INVALID_PASSWORD", ex.getError());
                    assertTrue(ex.getMessage().contains(NO_DIGIT_ERROR));
                    Assertions.assertEquals(422, ex.getHttpStatus());
                })
                .log()
                .verify();
    }

}