package br.com.passwordValidator.controller;

import br.com.passwordValidator.exception.PasswordCheckFailException;
import br.com.passwordValidator.model.request.PasswordRequest;
import br.com.passwordValidator.model.response.PasswordResponse;
import br.com.passwordValidator.service.impl.PasswordCheckResponseHandler;
import br.com.passwordValidator.service.impl.RegexPasswordValidatorImpl;
import br.com.passwordValidator.service.impl.regex.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.blockhound.BlockHound;
import reactor.blockhound.BlockingOperationError;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import static br.com.passwordValidator.helper.PasswordResponseBuilder.returnPasswordResponse_withNoLowerLetter_withNoSpecialChars_withNotEnoughLength;

@WebFluxTest(controllers = PasswordController.class, excludeAutoConfiguration = {ReactiveSecurityAutoConfiguration.class})
@Import({RegexPasswordValidatorImpl.class, RegexSpaceBetweenValidation.class, RegexDigitsValidation.class, RegexLowerLettersValidation.class, RegexPasswordLengthValidation.class, RegexRepetitionValidation.class, RegexSpecialCharsValidation.class, RegexUpperLettersValidation.class, PasswordCheckResponseHandler.class})
class PasswordControllerIT {

    @Autowired
    WebTestClient webTestClient;

    PasswordRequest passwordRequest = new PasswordRequest();

    @BeforeAll
    public static void blockHoundSetup() {
        BlockHound.install();
    }

    @Test
    public void blockHoundWorks() {
        try {
            FutureTask<?> task = new FutureTask<>(() -> {
                Thread.sleep(0); //NOSONAR
                return "";
            });
            Schedulers.parallel().schedule(task);

            task.get(10, TimeUnit.SECONDS);
            Assertions.fail("should fail");
        } catch (Exception e) {
            Assertions.assertTrue(e.getCause() instanceof BlockingOperationError);
        }
    }


    @Test
    public void givenAValidPassword_whenValidateAll_thenShouldPass() {
        passwordRequest.setPassword("1234a!B67");

        webTestClient
                .post()
                .uri("/password/check")
                .body(BodyInserters.fromValue(passwordRequest))
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.passwordCheck").isEqualTo(true);
    }


    @Test
    public void givenAInvalidPassword_whenValidateAll_thenReturnException() {
        passwordRequest.setPassword("1234B67");

        PasswordResponse response = returnPasswordResponse_withNoLowerLetter_withNoSpecialChars_withNotEnoughLength();
        PasswordCheckFailException exception = new PasswordCheckFailException(response.getErrorList().toString());

        webTestClient
                .post()
                .uri("/password/check")
                .body(BodyInserters.fromValue(passwordRequest))
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody()
                .jsonPath("error").isEqualTo(exception.getError())
                .jsonPath("error_description").isEqualTo(exception.getMessage());

    }

}