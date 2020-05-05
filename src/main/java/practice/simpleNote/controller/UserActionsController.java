package practice.simpleNote.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import practice.simpleNote.Constants.Constants;
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

    @PostMapping("/joinBoard")
    public ResponseEntity<BoardModel> joinBoard(@RequestBody BoardAndUserIds userBoardDTO)  {
        BoardModel board= userActionsService.joinBoard(userBoardDTO.userId, userBoardDTO.boardId);

        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @PostMapping("/leaveBoard")
    public ResponseEntity<String> leaveBoard(@RequestBody BoardAndUserIds boardAndUserIds)  {

        userActionsService.leaveBoard(boardAndUserIds.userId, boardAndUserIds.boardId);

        return new ResponseEntity<>(Constants.Success, HttpStatus.OK);
    }

    @PostMapping("/createBoard")
    public ResponseEntity<BoardModel> createBoard(@RequestBody CreateBoard createBoardDTO) {

        System.out.println("ARE WE EVEN HERE ?");
        System.out.println(createBoardDTO.toString());

        BoardModel board =  userActionsService.createUserBoard(createBoardDTO.userId, createBoardDTO.boardTitle);

        return new ResponseEntity<>(board, HttpStatus.OK);
    }

}



