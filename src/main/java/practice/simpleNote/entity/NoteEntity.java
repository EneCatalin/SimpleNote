package practice.simpleNote.entity;

import com.fasterxml.jackson.annotation.*;
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

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id_fk", referencedColumnName = "id")
    private BoardEntity boardEntity;

    public NoteEntity() {
    }

    public NoteEntity(@NotNull String title, @NotNull String content, String boardId) {
        this.title = title;
        this.content = content;
        this.boardEntity = new BoardEntity(boardId);
    }

    //TODO look into the boardEntity thingy. Is it clear we only take the id here ?
    public NoteEntity(String id, @NotNull String title, @NotNull String content, String boardId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.boardEntity=new BoardEntity(boardId);
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
        return boardEntity.getId();
    }

    public void setBoardId(String boardEntityId) {
        this.boardEntity = new BoardEntity(boardEntityId);
    }
}
