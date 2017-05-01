package ar.edu.untref.gio.presentation.response;

import org.codehaus.jackson.annotate.JsonProperty;

public class UserResponse {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("email")
    private String email;

    @JsonProperty("name")
    private String name;

    @JsonProperty("user_economy")
    private UserEconomyResponse userEconomyResponse;

    public UserResponse(Integer id, String email, String name, UserEconomyResponse userEconomyResponse) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.userEconomyResponse = userEconomyResponse;
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

    public UserEconomyResponse getUserEconomyResponse() {
        return userEconomyResponse;
    }
}
