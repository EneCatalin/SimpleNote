package practice.simpleNote.dto;

public class CreateBoard {
    public String userId;
    public String boardTitle;

    @Override
    public String toString() {
        return "CreateBoard{" +
                "userId='" + userId + '\'' +
                ", boardTitle='" + boardTitle + '\'' +
                '}';
    }
}
