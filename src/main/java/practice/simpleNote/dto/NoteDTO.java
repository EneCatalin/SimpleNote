package practice.simpleNote.dto;

import practice.simpleNote.Constants.Constants;

import javax.validation.constraints.NotBlank;

public class NoteDTO {
    @NotBlank(message = Constants.BlankNoteIdMessage)
    private final String id;
    @NotBlank(message = Constants.BlankNoteTitleMessage)
    private final String title;
    @NotBlank(message = Constants.blankNoteContentMessage)
    private final String content;
    @NotBlank(message = Constants.BlankBoardIdMessage)
    private final String boardId;

    public NoteDTO(String id, String title, String content, String boardId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.boardId = boardId;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getBoardId() {
        return boardId;
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
