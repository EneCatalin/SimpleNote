package practice.simpleNote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.simpleNote.entity.UserEntity;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    List<UserEntity> findByBoardsId(String boardId);

}

