package practice.simpleNote.service;

import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import practice.simpleNote.entity.BoardEntity;
import practice.simpleNote.entity.UserEntity;
import practice.simpleNote.repository.BoardRepository;
import practice.simpleNote.repository.UserRepository;

import java.util.Optional;

@Service
public class UserActionsService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;


    public UserActionsService(UserRepository userRepository, BoardRepository boardRepository) {
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
    }

    private UserEntity getUserEntity(String userId) throws ResponseStatusException {
        Optional<UserEntity> userEntity = userRepository.findById(userId);

        return userEntity.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "USER Not Found"));
    }

    private BoardEntity getBoardEntity(String boardId) throws ResponseStatusException {
        Optional<BoardEntity> boardEntity = boardRepository.findById(boardId);

        return boardEntity.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "BOARD Not Found"));
    }


    private void populateJoinBoard(UserEntity user, BoardEntity board){
        user.getBoards().add(board);
        board.getUsers().add(user);
    }

    //TODO consider a return type ?
    public void joinBoard(String userId, String boardId){
        UserEntity user = getUserEntity(userId);
        BoardEntity board=getBoardEntity(boardId);

        populateJoinBoard(user,board);

        userRepository.save(user);

    }

    public void leaveBoard(String userId,String boardId){
        UserEntity user = getUserEntity(userId);
        BoardEntity board=getBoardEntity(boardId);

        user.getBoards().remove(board);
        board.getUsers().remove(user);

        userRepository.save(user);
    }

}
