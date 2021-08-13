package br.com.passwordValidator.service.impl;

import br.com.passwordValidator.model.request.PasswordRequest;
import br.com.passwordValidator.model.response.PasswordResponse;
import br.com.passwordValidator.service.PasswordValidator;
import br.com.passwordValidator.service.impl.regex.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class RegexPasswordValidatorImpl implements PasswordValidator {

    private static final Logger LOG = LoggerFactory.getLogger(RegexPasswordValidatorImpl.class);

    private final RegexSpaceBetweenValidation regexSpaceBetweenValidation;
    private final RegexDigitsValidation regexDigitsValidation;
    private final RegexLowerLettersValidation regexLowerLettersValidation;
    private final RegexUpperLettersValidation regexUpperLettersValidation;
    private final RegexSpecialCharsValidation regexSpecialCharsValidation;
    private final RegexPasswordLengthValidation regexPasswordLengthValidation;
    private final RegexRepetitionValidation regexRepetitionValidation;
    private final PasswordCheckResponseHandler handler;

    public RegexPasswordValidatorImpl(RegexSpaceBetweenValidation regexSpaceBetweenValidation, RegexDigitsValidation regexDigitsValidation, RegexLowerLettersValidation regexLowerLettersValidation, RegexUpperLettersValidation regexUpperLettersValidation, RegexSpecialCharsValidation regexSpecialCharsValidation, RegexPasswordLengthValidation regexPasswordLengthValidation, RegexRepetitionValidation regexRepetitionValidation, PasswordCheckResponseHandler handler) {
        this.regexSpaceBetweenValidation = regexSpaceBetweenValidation;
        this.regexDigitsValidation = regexDigitsValidation;
        this.regexLowerLettersValidation = regexLowerLettersValidation;
        this.regexUpperLettersValidation = regexUpperLettersValidation;
        this.regexSpecialCharsValidation = regexSpecialCharsValidation;
        this.regexPasswordLengthValidation = regexPasswordLengthValidation;
        this.regexRepetitionValidation = regexRepetitionValidation;
        this.handler = handler;
    }

    @Override
    public Mono<PasswordResponse> checkPassword(PasswordRequest request) {

        String password = request.getPassword();

        return regexSpaceBetweenValidation.validate(password, new PasswordResponse())
                .doOnSuccess(response -> LOG.info("Validacao de espacos concluida, seguindo para digitos"))

                .flatMap(response -> regexDigitsValidation.validate(password, response))
                .doOnSuccess(response -> LOG.info("Validacao de digitos concluida, seguindo para letras minusculas"))

                .flatMap(response -> regexLowerLettersValidation.validate(password, response))
                .doOnSuccess(response -> LOG.info("Validacao de letras minusculas concluida, seguindo letras maiusculas"))

                .flatMap(response -> regexUpperLettersValidation.validate(password, response))
                .doOnSuccess(response -> LOG.info("Validacao de letras maiusculas concluida, seguindo caracteres especiais"))

                .flatMap(response -> regexSpecialCharsValidation.validate(password, response))
                .doOnSuccess(response -> LOG.info("Validacao de caracteres especiais concluida, seguindo para cumprimento da senha"))

                .flatMap(response -> regexPasswordLengthValidation.validate(password, response))
                .doOnSuccess(response -> LOG.info("Validacao de cumprimento da senha concluida, seguindo para repeticao de caracteres"))

                .flatMap(response -> regexRepetitionValidation.validate(password, response))

                .flatMap(handler::handleResponse);

    }
}
