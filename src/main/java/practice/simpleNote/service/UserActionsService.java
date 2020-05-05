package practice.simpleNote.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import practice.simpleNote.Constants.Constants;
import practice.simpleNote.entity.BoardEntity;
import practice.simpleNote.entity.NoteEntity;
import practice.simpleNote.entity.UserEntity;
import practice.simpleNote.model.BoardModel;
import practice.simpleNote.model.UserModel;
import practice.simpleNote.repository.BoardRepository;
import practice.simpleNote.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserActionsService {

    //This does not look right at all
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final BoardService boardActions;
    private final UserService userService;
    private final NoteActions noteActions;


    public UserActionsService(UserRepository userRepository, BoardRepository boardRepository, BoardService boardActions, UserService userService, NoteActions noteActions) {
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
        this.boardActions = boardActions;
        this.userService = userService;
        this.noteActions = noteActions;
    }


    public BoardModel createUserBoard (String userId, String boardTitle) {

        BoardEntity board =  boardRepository.save(new BoardEntity(boardTitle));

        joinBoard(userId,board.getId());

        return boardActions.boardEntityToModel(board);
    }


    public void leaveBoard(String userId,String boardId){

        UserEntity user = getUserEntity(userId);

        BoardEntity board=getBoardEntity(boardId);

        if(!isBoardMember(userId, boardId)){
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, Constants.NotAMemberOfThatBoard);
        }

        List<UserModel> userModels = userService.filterUsersByBoard(boardId);

        //check if there is only one member of that board
        //if yes the board can be deleted so as not to waste space
        if(userModels.size()== 1){
            System.out.println("BREAKPOINT 1");

            //since we don't have a one to many rel we also lack a cascade
            // so hibernate won't automatically delete the notes. Sad :(

            //Filter the notes by boardId
            Set<NoteEntity> notes= noteActions.filterNotesByBoardId(boardId);
            //delete the notes found
            noteActions.deleteNotes(notes);

            //remove the connection between user and board
            user.removeBoard(board);
            userRepository.save(user);
            //delete the board
            boardRepository.delete(board);

        }else{
            user.removeBoard(board);
            userRepository.save(user);

        }

    }



    public BoardModel joinBoard(String userId, String boardId){
        UserEntity user = getUserEntity(userId);

        BoardEntity board=getBoardEntity(boardId);

        // if the board with that id is already in the list throw an error
        if(isBoardMember(userId, boardId)){
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, Constants.AlreadyABoardMember);
        }

        user.addBoard(board);

        userRepository.save(user);

        return boardActions.getBoardModel(boardId);

    }

    private boolean isBoardMember(String userId, String boardId){

        List<BoardModel> boardModelList =boardActions.filterBoardsByUserId(userId);

        for (BoardModel board : boardModelList) {
            if(board.getId().equals(boardId)){
                return true;
            }
        }

        return false;
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




}
