package practice.simpleNote.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "notes")
public class NoteEntity {

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Id
    private String id;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id_fk", referencedColumnName = "id")
    private BoardEntity board;

    public NoteEntity() {
    }

    public NoteEntity(@NotNull String title, @NotNull String content, BoardEntity board) {
        this.title = title;
        this.content = content;
        this.board = board;
    }

    //TODO look into the boardEntity thingy. Is it clear we only take the id here ?
    public NoteEntity(String id, @NotNull String title, @NotNull String content, String boardId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.board= new BoardEntity(boardId);
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBoardId() {
        return board.getId();
    }

    public void setBoardEntity(String boardEntityId) {
        this.board = new BoardEntity(boardEntityId);
    }
}
