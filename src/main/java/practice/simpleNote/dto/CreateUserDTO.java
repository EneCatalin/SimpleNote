package practice.simpleNote.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import practice.simpleNote.Constants.Constants;

import javax.validation.constraints.NotBlank;

public class CreateUserDTO {

    @NotBlank(message = Constants.BlankUsernameMessage)
    private String username;

    @JsonCreator
    public CreateUserDTO(@JsonProperty("username") String username) {
        this.username = username;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
