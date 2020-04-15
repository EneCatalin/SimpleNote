package practice.simpleNote.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "boards")
public class BoardEntity {


    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Id
    private String id;

    @NotNull
    @Column(name="title")
    private String title;

//    @JsonManagedReference
    @OneToMany(mappedBy = "boardEntity")
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

    //TODO think about this. Do we really need a setter here or do we need an add ?
    public void setNotes(List<NoteEntity> notes) {
        this.notes = notes;
    }

    //TODO look over this. So, again, should i do it like this ? It makes more sense in a way
    //Then again, notes should have crud done on them. So add/remove/delete/update
    //is boardEntity even the place to define such operations ??? JpaRepository should handle all of this
    public void addNotes(NoteEntity notes) {
        this.notes.add(notes);
    }

}
