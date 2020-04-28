package practice.simpleNote.dto;

public class DeleteNoteDTO {
    public String boardId;
    public String noteId;

    @Override
    public String toString() {
        return "DeleteNoteDTO{" +
                "boardId='" + boardId + '\'' +
                ", noteId='" + noteId + '\'' +
                '}';
    }
}
