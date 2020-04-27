package practice.simpleNote.dto;

public class NoteDTO {
    public String id;
    public String title;
    public String content;
    public String boardId;

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
