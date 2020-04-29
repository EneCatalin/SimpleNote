package practice.simpleNote.dto;

//No idea how to name this without it sounding stupid
public class BoardAndUserIds {
    public String userId;
    public String boardId;

    @Override
    public String toString() {
        return "BoardAndUserIds{" +
                "userId='" + userId + '\'' +
                ", boardId='" + boardId + '\'' +
                '}';
    }
}
