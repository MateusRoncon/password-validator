package br.com.passwordValidator.exception;

public class PasswordCheckFailException extends PasswordValidatorException {

    public PasswordCheckFailException(String message) {
        super(message, 422, "INVALID_PASSWORD");
    }
}
