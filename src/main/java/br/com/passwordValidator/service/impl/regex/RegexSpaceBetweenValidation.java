package br.com.passwordValidator.service.impl.regex;

import br.com.passwordValidator.model.response.PasswordResponse;
import br.com.passwordValidator.service.impl.RegexWorkflowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static br.com.passwordValidator.util.Messages.NO_SPACES_BETWEEN_ERROR;
import static br.com.passwordValidator.util.RegexConstants.HAS_SPACE_BETWEEN_REGEX;

@Component
public class RegexSpaceBetweenValidation implements RegexWorkflowService {

    private static final Logger LOG = LoggerFactory.getLogger(RegexSpaceBetweenValidation.class);

    @Override
    public Mono<PasswordResponse> validate(String password, PasswordResponse response) {
        if (password.matches(HAS_SPACE_BETWEEN_REGEX)) {
            LOG.info("Senha possui espaco(s) em branco, adicionando retorno ao usuario");
            response.getErrorList().add(NO_SPACES_BETWEEN_ERROR);
        }
        return Mono.just(response);
    }
}
