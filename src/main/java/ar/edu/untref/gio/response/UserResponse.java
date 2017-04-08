package ar.edu.untref.gio.response;

import org.codehaus.jackson.annotate.JsonProperty;

public class UserResponse {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("email")
    private String email;

    @JsonProperty("name")
    private String name;

    public UserResponse(Integer id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
