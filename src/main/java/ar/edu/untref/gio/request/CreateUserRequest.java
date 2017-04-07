package ar.edu.untref.gio.request;

import org.codehaus.jackson.annotate.JsonProperty;

public class CreateUserRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    public CreateUserRequest() {}

    public CreateUserRequest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public void setEmail(String email) { this.email = email; }

    public void setPassword(String password) { this.password = password; }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
