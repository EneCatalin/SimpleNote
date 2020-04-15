package practice.simpleNote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.simpleNote.entity.NoteEntity;

@Repository
public interface NoteRepository extends JpaRepository<NoteEntity,String> {
}
