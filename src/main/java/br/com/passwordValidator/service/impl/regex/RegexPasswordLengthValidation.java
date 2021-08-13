package br.com.passwordValidator.service.impl.regex;

import br.com.passwordValidator.model.response.PasswordResponse;
import br.com.passwordValidator.service.impl.RegexWorkflowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static br.com.passwordValidator.util.Messages.NOT_ENOUGH_LENGTH_ERROR;
import static br.com.passwordValidator.util.RegexConstants.LENGTH_REGEX;

@Component
public class RegexPasswordLengthValidation implements RegexWorkflowService {

    private static final Logger LOG = LoggerFactory.getLogger(RegexPasswordLengthValidation.class);


    @Override
    public Mono<PasswordResponse> validate(String password, PasswordResponse response) {
        if (!password.matches(LENGTH_REGEX)) {
            LOG.info("Senha nao possui caracteres especiais, adicionando retorno ao usuario");
            response.getErrorList().add(NOT_ENOUGH_LENGTH_ERROR);
        }
        return Mono.just(response);
    }
}
