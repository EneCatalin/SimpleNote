package practice.simpleNote.model;

import java.util.Set;

public class BoardModel {

    private String id;

    private String title;

    private Set<NoteModel> notes;

    public BoardModel(String id, String title, Set<NoteModel> notes) {
        this.id = id;
        this.title = title;
        this.notes = notes;
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

    public Set<NoteModel> getNotes() {
        return notes;
    }

    public void setNotes(Set<NoteModel> notes) {
        this.notes = notes;
    }

}
