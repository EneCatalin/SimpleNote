package practice.simpleNote.model;

public class NoteModel {

    String id;
    String title;
    String content;
    String boardId;

    public NoteModel(String id, String title, String content, String boardId) {
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "NoteModel{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", boardId='" + boardId + '\'' +
                '}';
    }
}
