package practice.simpleNote.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import practice.simpleNote.Constants.Constants;

import javax.validation.constraints.NotBlank;

//No idea how to name this without it sounding stupid
public class BoardAndUserIdsDTO {
    @NotBlank(message = Constants.BlankUserIdMessage)
    private final String userId;
    @NotBlank(message = Constants.BlankBoardIdMessage)
    private final String boardId;

    @JsonCreator
    public BoardAndUserIdsDTO(@JsonProperty("userId") String userId, @JsonProperty("boardId") String boardId) {
        this.userId = userId;
        this.boardId = boardId;
    }

    public String getUserId() {
        return userId;
    }

    public String getBoardId() {
        return boardId;
    }

    @Override
    public String toString() {
        return "BoardAndUserIds{" +
                "userId='" + userId + '\'' +
                ", boardId='" + boardId + '\'' +
                '}';
    }
}
