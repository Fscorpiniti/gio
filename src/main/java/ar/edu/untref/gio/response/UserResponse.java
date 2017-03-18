package ar.edu.untref.gio.response;

import org.codehaus.jackson.annotate.JsonProperty;

public class UserResponse {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("email")
    private String email;

    public UserResponse(Integer id, String email) {
        this.id = id;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
