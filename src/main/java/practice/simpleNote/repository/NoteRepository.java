package practice.simpleNote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import practice.simpleNote.entity.BoardEntity;
import practice.simpleNote.entity.NoteEntity;

import java.util.Set;

@Repository
public interface NoteRepository extends JpaRepository<NoteEntity,String> {

//    THIS ALSO WORKS !!!
//    @Query( value = "SELECT * FROM notes WHERE board_id_fk = (:board) ", nativeQuery = true)
//    Set<NoteEntity> findByboardId(@Param("board") String boardId);

    Set<NoteEntity> findByboard(BoardEntity board);

//    THIS ALSO WORKS !!!
    @Query( value = "SELECT * FROM notes WHERE board_id_fk = (:board) ", nativeQuery = true)
    Set<NoteEntity> findByboardId(@Param("board") String boardId);

}
