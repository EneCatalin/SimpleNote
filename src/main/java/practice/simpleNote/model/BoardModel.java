package practice.simpleNote.model;

import practice.simpleNote.entity.NoteEntity;

import java.util.List;

public class BoardModel {

    private String id;

    private String title;

    //TODO make this a noteModel maybe ?
    private List<NoteEntity> notes;

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

    public List<NoteEntity> getNotes() {
        return notes;
    }

    public void setNotes(List<NoteEntity> notes) {
        this.notes = notes;
    }
}
