package practice.simpleNote.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Id
    private String id;

    @NotNull
    @Column(name="username")
    private String username;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "user_board",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "board_id")
    )

    private Set<BoardEntity> boards = new HashSet<>();

    public UserEntity() {
    }

    public UserEntity(@NotNull String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<BoardEntity> getBoards() {
        return boards;
    }

    public void setBoards(Set<BoardEntity> boards) {
        this.boards = boards;
    }

    public void addBoard(BoardEntity board){
        this.boards.add(board);
        board.getUsers().add(this);
    }

    public void removeBoard(BoardEntity board){
        this.boards.remove(board);
        board.getUsers().remove(this);
    }

}