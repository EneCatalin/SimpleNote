package practice.simpleNote.entity;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "boards")
    private Set<UserEntity> users = new HashSet<>();



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
    public void addNotes(NoteEntity notes) {
        this.notes.add(notes);
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }
}
