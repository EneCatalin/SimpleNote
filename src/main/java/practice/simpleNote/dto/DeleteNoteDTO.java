package practice.simpleNote.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import practice.simpleNote.Constants.Constants;

import javax.validation.constraints.NotBlank;

public class DeleteNoteDTO {
    @NotBlank(message = Constants.BlankBoardIdMessage)
    private final String boardId;
    @NotBlank(message = Constants.BlankNoteIdMessage)
    private final String noteId;

    @JsonCreator
    public DeleteNoteDTO(@JsonProperty("boardId") String boardId, @JsonProperty("noteId") String noteId) {
        this.boardId = boardId;
        this.noteId = noteId;
    }

    public String getBoardId() {
        return boardId;
    }

    public String getNoteId() {
        return noteId;
    }

    @Override
    public String toString() {
        return "DeleteNoteDTO{" +
                "boardId='" + boardId + '\'' +
                ", noteId='" + noteId + '\'' +
                '}';
    }
}
