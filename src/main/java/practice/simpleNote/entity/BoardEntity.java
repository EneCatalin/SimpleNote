package practice.simpleNote.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
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
                    CascadeType.MERGE,
            },
            mappedBy = "boards")
    private Set<UserEntity> users = new HashSet<>();

    public BoardEntity() {
    }

    public BoardEntity(String id, @NotNull String title) {
        this.id = id;
        this.title = title;
    }

    public BoardEntity(@NotNull String title) {
        this.title= title;
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

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "BoardEntity{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
