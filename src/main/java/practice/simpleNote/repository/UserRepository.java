package practice.simpleNote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.simpleNote.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
}

