package br.com.passwordValidator.exception;

public class PasswordValidatorException extends RuntimeException {

    private int httpStatus;
    private String error;

    public PasswordValidatorException(String message, int httpStatus, String error) {
        super(message);
        this.httpStatus = httpStatus;
        this.error = error;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return "PasswordValidatorException{" + "httpStatus=" + httpStatus +
                ", error='" + error + '\'' +
                '}';
    }
}
