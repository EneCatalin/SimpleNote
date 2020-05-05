package practice.simpleNote.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import practice.simpleNote.Constants.Constants;

import javax.validation.constraints.NotBlank;

public class UserDTO {

    @NotBlank(message = Constants.BlankUserIdMessage)
    private final String id;
    @NotBlank(message = Constants.BlankUsernameMessage)
    private final String username;

    @JsonCreator
    public UserDTO(@JsonProperty("id") String id,@JsonProperty("username") String username) {
        this.id = id;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
