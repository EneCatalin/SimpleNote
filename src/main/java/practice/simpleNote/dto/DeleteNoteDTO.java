package practice.simpleNote.dto;

public class DeleteNoteDTO {
    public String boardId;
    public String noteId;

    //For deserialisation purposes the DTO must have a zero-arg constructor.
    //THE ABOVE IS SUPER TRIGGERING
    public DeleteNoteDTO() {
    }

    @Override
    public String toString() {
        return "DeleteNoteDTO{" +
                "boardId='" + boardId + '\'' +
                ", noteId='" + noteId + '\'' +
                '}';
    }
}
