package ar.edu.untref.gio.dto;

import org.codehaus.jackson.annotate.JsonProperty;

public class CreateUserDTO {

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    public CreateUserDTO() {}

    public void setEmail(String email) { this.email = email; }

    public void setPassword(String password) { this.password = password; }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
