package practice.simpleNote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.simpleNote.entity.BoardEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity,String> {
}
