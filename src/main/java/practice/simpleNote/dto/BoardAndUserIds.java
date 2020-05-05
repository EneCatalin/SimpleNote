package practice.simpleNote.dto;

//No idea how to name this without it sounding stupid
public class BoardAndUserIds {
    public String userId;
    public String boardId;

    //For deserialisation purposes the DTO must have a zero-arg constructor.
    //THE ABOVE IS SUPER TRIGGERING
    public BoardAndUserIds() {
    }

    @Override
    public String toString() {
        return "BoardAndUserIds{" +
                "userId='" + userId + '\'' +
                ", boardId='" + boardId + '\'' +
                '}';
    }
}
