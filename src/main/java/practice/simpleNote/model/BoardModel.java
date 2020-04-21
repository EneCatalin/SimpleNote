package practice.simpleNote.model;

public class BoardModel {

    private String id;

    private String title;

    //TODO make this a noteModel maybe ?
//    private List<NoteEntity> notes;

    public BoardModel(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
