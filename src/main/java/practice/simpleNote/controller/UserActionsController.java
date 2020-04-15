package practice.simpleNote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import practice.simpleNote.entity.BoardEntity;
import practice.simpleNote.entity.UserEntity;
import practice.simpleNote.model.BoardModel;
import practice.simpleNote.model.UserModel;
import practice.simpleNote.repository.BoardRepository;
import practice.simpleNote.repository.UserRepository;
import practice.simpleNote.service.BoardService;
import practice.simpleNote.service.UserService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/userActions")
public class UserActionsController {



@Autowired
private  UserRepository userRepo;
@Autowired
private  BoardRepository boardRepo;

    private UserEntity getUserEntity(String userId) throws ResponseStatusException {
        Optional<UserEntity> userEntity = userRepo.findById(userId);

        return userEntity.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "USER Not Found"));
    }

    private BoardEntity getBoardEntity(String boardId) throws ResponseStatusException {
        Optional<BoardEntity> boardEntity = boardRepo.findById(boardId);

        return boardEntity.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "BOARD Not Found"));
    }

    @ExceptionHandler({ResponseStatusException.class})
    @PostMapping("/BOARD")
    public void createBoard() throws Exception {

        UserEntity user1 = getUserEntity("123");
        BoardEntity board1=getBoardEntity("1A");
        BoardEntity board2=getBoardEntity("2A");


        user1.getBoards().add(board1);
        user1.getBoards().add(board2);

        board1.getUsers().add(user1);
        board2.getUsers().add(user1);

        userRepo.save(user1);
    }





//    private final UserService usersService;
//
//    private final BoardService boardService;
//
//
//    public UserActionsController(UserService usersService, BoardService boardService) {
//        this.usersService = usersService;
//        this.boardService = boardService;
//    }
//
//    @ExceptionHandler({ResponseStatusException.class})
//    @PostMapping("/createBoard")
//    public ResponseEntity<BoardModel> createUser(@RequestBody @Valid BoardModel board) throws Exception {
//
////        return new ResponseEntity<>(usersService.createUser(user), HttpStatus.OK);
//    }

}
