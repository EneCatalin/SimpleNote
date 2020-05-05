package practice.simpleNote.dto;

import practice.simpleNote.Constants.Constants;

import javax.validation.constraints.NotBlank;

public class CreateUserDTO {

    @NotBlank(message = Constants.BlankUserNameModelMessage)
    public String username;


}
