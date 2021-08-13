package br.com.passwordValidator.service;

import br.com.passwordValidator.model.request.PasswordRequest;
import br.com.passwordValidator.model.response.PasswordResponse;
import reactor.core.publisher.Mono;

public interface PasswordValidator {

    Mono<PasswordResponse> checkPassword(PasswordRequest request);

}
