package br.com.passwordValidator.controller;

import br.com.passwordValidator.model.request.PasswordRequest;
import br.com.passwordValidator.model.response.PasswordResponse;
import br.com.passwordValidator.service.PasswordValidator;
import br.com.passwordValidator.service.impl.RegexPasswordValidatorImpl;
import br.com.passwordValidator.service.impl.regex.RegexLowerLettersValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/password/check")
@Validated
public class PasswordController {

    private static final Logger LOG = LoggerFactory.getLogger(RegexLowerLettersValidation.class);

    private final PasswordValidator validator;

    public PasswordController(RegexPasswordValidatorImpl validator) {
        this.validator = validator;
    }


    @PostMapping
    public Mono<PasswordResponse> checkPassword(@RequestBody @Valid PasswordRequest request) {
        LOG.info("Iniciando validacao de senha");
        return validator.checkPassword(request);
    }
}
