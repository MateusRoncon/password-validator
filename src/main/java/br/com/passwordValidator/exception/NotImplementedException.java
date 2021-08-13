package br.com.passwordValidator.exception;

public class NotImplementedException extends PasswordValidatorException {

    public NotImplementedException(String message) {
        super(message, 500, "NOT_IMPLEMENTED_EXCEPTION");
    }
}
