package practice.simpleNote.model;

public class NoteModel {

    String id;
    String title;
    String content;

    public NoteModel(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public NoteModel(String id, String title, String content, String boardId) {
        this.id = id;
        this.title = title;
        this.content = content;
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


    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
