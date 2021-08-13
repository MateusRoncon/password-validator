package br.com.passwordValidator.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PasswordResponse {

    private Boolean passwordCheck;
    private ArrayList<String> errorList = new ArrayList<>();

    public PasswordResponse() {
    }

    public Boolean getPasswordCheck() {
        return passwordCheck;
    }

    public void setPasswordCheck(Boolean passwordCheck) {
        this.passwordCheck = passwordCheck;
    }

    public List<String> getErrorList() {
        return errorList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PasswordResponse{");
        sb.append("passwordCheck=").append(passwordCheck);
        sb.append(", errorList=").append(errorList);
        sb.append('}');
        return sb.toString();
    }
}

