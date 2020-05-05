package practice.simpleNote.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import practice.simpleNote.Constants.Constants;

import javax.validation.constraints.NotBlank;

public class CreateBoard {
    @NotBlank(message = Constants.BlankUserIdMessage)
    private String userId;
    @NotBlank(message = Constants.BlankBoardTitleMessage)
    private String boardTitle;

    @JsonCreator
    public CreateBoard(@JsonProperty("userId") String userId,@JsonProperty("boardTitle") String boardTitle) {
        this.userId = userId;
        this.boardTitle = boardTitle;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    @Override
    public String toString() {
        return "CreateBoard{" +
                "userId='" + userId + '\'' +
                ", boardTitle='" + boardTitle + '\'' +
                '}';
    }
}
