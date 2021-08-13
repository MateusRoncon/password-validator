package br.com.passwordValidator.service.impl.regex;

import br.com.passwordValidator.model.response.PasswordResponse;
import br.com.passwordValidator.service.impl.RegexWorkflowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static br.com.passwordValidator.util.Messages.NO_SPECIAL_CHARS_ERROR;
import static br.com.passwordValidator.util.RegexConstants.HAS_SPECIAL_CHAR_REGEX;

@Component
public class RegexSpecialCharsValidation implements RegexWorkflowService {

    private static final Logger LOG = LoggerFactory.getLogger(RegexSpecialCharsValidation.class);


    @Override
    public Mono<PasswordResponse> validate(String password, PasswordResponse response) {
        if (!password.matches(HAS_SPECIAL_CHAR_REGEX)) {
            LOG.info("Senha nao possui caracteres especiais, adicionando retorno ao usuario");
            response.getErrorList().add(NO_SPECIAL_CHARS_ERROR);
        }
        return Mono.just(response);
    }
}
