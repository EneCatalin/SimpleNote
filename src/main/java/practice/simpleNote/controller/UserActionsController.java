package practice.simpleNote.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import practice.simpleNote.dto.BoardAndUserIds;
import practice.simpleNote.dto.CreateBoard;
import practice.simpleNote.model.BoardModel;
import practice.simpleNote.service.UserActionsService;

@RestController
@RequestMapping("/userActions")
public class UserActionsController {

    private final UserActionsService userActionsService;

    public UserActionsController(UserActionsService userActionsService) {
        this.userActionsService = userActionsService;
    }

    @ExceptionHandler({ResponseStatusException.class})
    @PostMapping("/joinBoard")
    public ResponseEntity<BoardModel> joinBoard(@RequestBody BoardAndUserIds userBoardpojo) throws Exception {
        BoardModel board= userActionsService.joinBoard(userBoardpojo.userId, userBoardpojo.boardId);

        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    //TODO fix the return statement
    //TODO make it so you can't leave a board you are not part of
    @ExceptionHandler({ResponseStatusException.class})
    @PostMapping("/leaveBoard")
    public ResponseEntity<String> leaveBoard(@RequestBody BoardAndUserIds boardAndUserIds) throws Exception {
        userActionsService.leaveBoard(boardAndUserIds.userId, boardAndUserIds.boardId);

        return new ResponseEntity<>("Action was successful", HttpStatus.OK);
    }

    @ExceptionHandler({ResponseStatusException.class})
    @PostMapping("/createBoard")
    public ResponseEntity<String> createBoard(@RequestBody CreateBoard createBoardDTO) throws Exception {
        userActionsService.createUserBoard(createBoardDTO.userId, createBoardDTO.boardTitle);

        return new ResponseEntity<>("Temporary Ok Message", HttpStatus.OK);
    }

}



