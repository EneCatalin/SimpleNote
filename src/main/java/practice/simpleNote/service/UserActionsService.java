package practice.simpleNote.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import practice.simpleNote.Constants.Constants;
import practice.simpleNote.entity.BoardEntity;
import practice.simpleNote.entity.UserEntity;
import practice.simpleNote.model.BoardModel;
import practice.simpleNote.model.UserModel;
import practice.simpleNote.repository.BoardRepository;
import practice.simpleNote.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserActionsService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final BoardService boardActions;
    private final UserService userService;


    public UserActionsService(UserRepository userRepository, BoardRepository boardRepository, BoardService boardActions, UserService userService) {
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
        this.boardActions = boardActions;
        this.userService = userService;
    }



    //TODO check if user is a board member
    // return a UserModel ???
    public void leaveBoard(String userId,String boardId){

        UserEntity user = getUserEntity(userId);

        BoardEntity board=getBoardEntity(boardId);

        List<UserModel> userModels = userService.filterUsersByBoard(boardId);

        //check if there is only one member of that board
        //if yes the board can be deleted so as not to waste space
        if(userModels.size()== 1){

            //remove the connection
            user.removeBoard(board);
            userRepository.save(user);
            //leave the board
            boardRepository.delete(board);

        }else{
            user.removeBoard(board);
            userRepository.save(user);

        }

    }

    public BoardModel createUserBoard (String userId, String boardTitle) {

        BoardEntity board =  boardRepository.save(new BoardEntity(boardTitle));

        joinBoard(userId,board.getId());

        return boardActions.boardEntityToModel(board);
    }


    public BoardModel joinBoard(String userId, String boardId){
        UserEntity user = getUserEntity(userId);

        BoardEntity board=getBoardEntity(boardId);

        user.addBoard(board);

        userRepository.save(user);

        return boardActions.getBoardModel(boardId);

    }

    private UserModel toModel(UserEntity userEntity) {
        return new UserModel(userEntity.getId(), userEntity.getUsername());
    }

    private UserEntity getUserEntity(String userId) throws ResponseStatusException {
        Optional<UserEntity> userEntity = userRepository.findById(userId);

        return userEntity.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, Constants.UserNotFound));
    }


    private BoardEntity getBoardEntity(String boardId) throws ResponseStatusException {
        Optional<BoardEntity> boardEntity = boardRepository.findById(boardId);

        return boardEntity.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, Constants.BoardNotFound));
    }


//    private void addUserToBoard(UserEntity user, BoardEntity board){
//        user.getBoards().add(board);
//        board.getUsers().add(user);
//    }
//
//    private void removeUserFromBoard(UserEntity user, BoardEntity board){
//        user.getBoards().remove(board);
//        board.getUsers().remove(user);
//    }



}
