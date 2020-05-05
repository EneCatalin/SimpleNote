package practice.simpleNote.dto;

public class CreateBoard {
    public String userId;
    public String boardTitle;

    //For deserialisation purposes the DTO must have a zero-arg constructor.
    //THE ABOVE IS SUPER TRIGGERING
    public CreateBoard() {
    }

    @Override
    public String toString() {
        return "CreateBoard{" +
                "userId='" + userId + '\'' +
                ", boardTitle='" + boardTitle + '\'' +
                '}';
    }
}
