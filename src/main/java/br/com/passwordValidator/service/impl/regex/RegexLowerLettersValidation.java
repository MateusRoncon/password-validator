package br.com.passwordValidator.service.impl.regex;

import br.com.passwordValidator.model.response.PasswordResponse;
import br.com.passwordValidator.service.impl.RegexWorkflowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static br.com.passwordValidator.util.Messages.NO_LOWER_LETTERS_ERROR;
import static br.com.passwordValidator.util.RegexConstants.LOWER_LETTER_REGEX;

@Component
public class RegexLowerLettersValidation implements RegexWorkflowService {

    private static final Logger LOG = LoggerFactory.getLogger(RegexLowerLettersValidation.class);


    @Override
    public Mono<PasswordResponse> validate(String password, PasswordResponse response) {
        if (!password.matches(LOWER_LETTER_REGEX)) {
            LOG.info("Senha nao possui letras minusculas, adicionando retorno ao usuario");
            response.getErrorList().add(NO_LOWER_LETTERS_ERROR);
        }
        return Mono.just(response);
    }
}
