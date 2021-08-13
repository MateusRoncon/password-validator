package br.com.passwordValidator.service.impl;

import br.com.passwordValidator.exception.NotImplementedException;
import br.com.passwordValidator.model.response.PasswordResponse;
import reactor.core.publisher.Mono;

public interface RegexWorkflowService {

    default Mono<PasswordResponse> validate(String password, PasswordResponse response) {
        return Mono.error(new NotImplementedException("Fluxo de validacao nao implementado!"));
    }

    default Mono<PasswordResponse> handleResponse(PasswordResponse response) {
        return Mono.error(new NotImplementedException("response handler nao implementado!"));
    }

}
