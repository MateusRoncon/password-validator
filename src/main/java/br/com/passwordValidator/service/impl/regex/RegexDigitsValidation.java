package br.com.passwordValidator.service.impl.regex;

import br.com.passwordValidator.model.response.PasswordResponse;
import br.com.passwordValidator.service.impl.RegexWorkflowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static br.com.passwordValidator.util.Messages.NO_DIGIT_ERROR;
import static br.com.passwordValidator.util.RegexConstants.HAS_DIGIT_REGEX;

@Component
public class RegexDigitsValidation implements RegexWorkflowService {

    private static final Logger LOG = LoggerFactory.getLogger(RegexDigitsValidation.class);

    @Override
    public Mono<PasswordResponse> validate(String password, PasswordResponse response) {
        if (!password.matches(HAS_DIGIT_REGEX)) {
            LOG.info("Senha nao possui numero, adicionando retorno ao usuario");
            response.getErrorList().add(NO_DIGIT_ERROR);
        }
        return Mono.just(response);
    }
}
