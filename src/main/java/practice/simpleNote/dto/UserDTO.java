package practice.simpleNote.dto;

import practice.simpleNote.Constants.Constants;

import javax.validation.constraints.NotBlank;

public class UserDTO {

    @NotBlank(message = Constants.BlankUserIdMessage)
    public String id;
    @NotBlank(message = Constants.BlankUsernameMessage)
    public String username;

    //For deserialisation purposes the DTO must have a zero-arg constructor.
    //THE ABOVE IS SUPER TRIGGERING
    public UserDTO() {
    }
}
