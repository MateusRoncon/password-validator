package br.com.passwordValidator.service.impl.regex;

import br.com.passwordValidator.model.response.PasswordResponse;
import br.com.passwordValidator.service.impl.RegexWorkflowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static br.com.passwordValidator.util.Messages.NO_UPPER_LETTERS_ERROR;
import static br.com.passwordValidator.util.RegexConstants.HAS_UPPER_LETTERS_REGEX;

@Component
public class RegexUpperLettersValidation implements RegexWorkflowService {

    private static final Logger LOG = LoggerFactory.getLogger(RegexUpperLettersValidation.class);


    @Override
    public Mono<PasswordResponse> validate(String password, PasswordResponse response) {
        if (!password.matches(HAS_UPPER_LETTERS_REGEX)) {
            LOG.info("Senha nao possui letras maiusculas, adicionando retorno ao usuario");
            response.getErrorList().add(NO_UPPER_LETTERS_ERROR);
        }
        return Mono.just(response);
    }
}
