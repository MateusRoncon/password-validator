package br.com.passwordValidator.model.request;


import javax.validation.constraints.NotBlank;

public class PasswordRequest {

    @NotBlank
    private String password;

    public PasswordRequest() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
