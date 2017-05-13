package ar.edu.untref.gio.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiError {

    @JsonProperty("message")
    private String message;

    public ApiError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
