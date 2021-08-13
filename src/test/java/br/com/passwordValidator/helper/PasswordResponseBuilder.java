package br.com.passwordValidator.helper;

import br.com.passwordValidator.model.response.PasswordResponse;

import static br.com.passwordValidator.util.Messages.*;

public class PasswordResponseBuilder {

    public static PasswordResponse returnPasswordResponse_withNoLowerLetter_withNoSpecialChars_withNotEnoughLength() {

        PasswordResponse response = new PasswordResponse();
        response.getErrorList().add(NO_LOWER_LETTERS_ERROR);
        response.getErrorList().add(NO_SPECIAL_CHARS_ERROR);
        response.getErrorList().add(NOT_ENOUGH_LENGTH_ERROR);

        return response;
    }

}
