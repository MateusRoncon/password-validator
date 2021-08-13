package br.com.passwordValidator.service.impl.regex;

import br.com.passwordValidator.model.response.PasswordResponse;
import br.com.passwordValidator.service.impl.RegexWorkflowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static br.com.passwordValidator.util.Messages.REPETITIVE_CHARACTER_ERROR;
import static br.com.passwordValidator.util.RegexConstants.HAS_REPETITION_REGEX;

@Component
public class RegexRepetitionValidation implements RegexWorkflowService {

    private static final Logger LOG = LoggerFactory.getLogger(RegexRepetitionValidation.class);


    @Override
    public Mono<PasswordResponse> validate(String password, PasswordResponse response) {
        if (password.matches(HAS_REPETITION_REGEX)) {
            LOG.info("Senha possui caracteres repetidos, adicionando retorno ao usuario");
            response.getErrorList().add(REPETITIVE_CHARACTER_ERROR);
        }
        return Mono.just(response);
    }
}
