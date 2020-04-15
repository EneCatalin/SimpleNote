package practice.simpleNote.model;

import javax.validation.constraints.NotBlank;

public class UserModel {
    private String id;
    @NotBlank(message = "Please enter a username for the user!")
    private String username;

    public UserModel(String id, String username) {
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
