package practice.simpleNote.dto;

public class NoteDTO {
    public String id;
    public String title;
    public String content;
    public String boardId;

    //For deserialisation purposes the DTO must have a zero-arg constructor.
    //THE ABOVE IS SUPER TRIGGERING
    public NoteDTO() {
    }

    @Override
    public String toString() {
        return "NoteDTO{" +
                "noteId='" + id + '\'' +
                ", noteTitle='" + title + '\'' +
                ", noteContent='" + content + '\'' +
                ", boardId='" + boardId + '\'' +
                '}';
    }
}
