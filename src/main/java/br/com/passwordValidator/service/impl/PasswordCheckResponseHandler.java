package br.com.passwordValidator.service.impl;

import br.com.passwordValidator.exception.PasswordCheckFailException;
import br.com.passwordValidator.model.response.PasswordResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class PasswordCheckResponseHandler implements RegexWorkflowService {

    private static final Logger LOG = LoggerFactory.getLogger(PasswordCheckResponseHandler.class);


    @Override
    public Mono<PasswordResponse> handleResponse(PasswordResponse response) {
        if (!response.getErrorList().isEmpty()) {
            LOG.info("Validacao finalizada e reprovada com as seguintes mensagens: {}", response.getErrorList());
            response.setPasswordCheck(false);
            return Mono.error(new PasswordCheckFailException(response.getErrorList().toString()));
        } else {
            response.setPasswordCheck(true);
            LOG.info("Validacao finalizada e aprovada com sucesso! {}", response);
        }
        return Mono.just(response);
    }
}
