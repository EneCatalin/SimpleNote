package practice.simpleNote.dto;

import practice.simpleNote.Constants.Constants;

import javax.validation.constraints.NotBlank;

public class CreateUserDTO {

    @NotBlank(message = Constants.BlankUsernameMessage)
    public String username;


    //For deserialisation purposes the DTO must have a zero-arg constructor.
    //THE ABOVE IS SUPER TRIGGERING
    public CreateUserDTO() {
    }

    public CreateUserDTO(@NotBlank(message = Constants.BlankUsernameMessage) String username) {
        this.username = username;
    }


}