package practice.simpleNote.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import practice.simpleNote.Constants.Constants;

import javax.validation.constraints.NotBlank;

public class CreateBoard {
    @NotBlank(message = Constants.BlankUserIdMessage)
    private final String userId;
    @NotBlank(message = Constants.BlankBoardTitleMessage)
    private final String boardTitle;

    @JsonCreator
    public CreateBoard(@JsonProperty("userId") String userId,@JsonProperty("boardTitle") String boardTitle) {
        this.userId = userId;
        this.boardTitle = boardTitle;
    }

    public String getUserId() {
        return userId;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    @Override
    public String toString() {
        return "CreateBoard{" +
                "userId='" + userId + '\'' +
                ", boardTitle='" + boardTitle + '\'' +
                '}';
    }
}
