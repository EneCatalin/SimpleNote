package practice.simpleNote.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import practice.simpleNote.service.UserActionsService;

@RestController
@RequestMapping("/userActions")
public class UserActionsController {

private final UserActionsService userActionsService;

    public UserActionsController(UserActionsService userActionsService) {
        this.userActionsService = userActionsService;
    }


    //TODO fix the return statement
    //TODO make it so you can't join the same board twice
    @ExceptionHandler({ResponseStatusException.class})
    @PostMapping("/joinBoard")
    public ResponseEntity<String> joinBoard(@RequestBody UserBoardPojo userBoardpojo) throws Exception {
        userActionsService.joinBoard(userBoardpojo.userId,userBoardpojo.boardId);

        return new ResponseEntity<>("Temporary Ok Message", HttpStatus.OK);
    }

    //TODO fix the return statement
    //TODO make it so you can't leave a board you are not part of
    @ExceptionHandler({ResponseStatusException.class})
    @PostMapping("/leaveBoard")
    public ResponseEntity<String> leaveBoard(@RequestBody UserBoardPojo userBoardId) throws Exception {
        userActionsService.leaveBoard(userBoardId.userId,userBoardId.boardId);

        return new ResponseEntity<>("Temporary Ok Message", HttpStatus.OK);
    }

}

//TODO figure out what to do with this
class UserBoardPojo{
    public String userId;
    public String boardId;
}