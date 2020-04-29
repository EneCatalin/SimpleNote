package practice.simpleNote.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import practice.simpleNote.customExceptions.UserNotFoundException;
import practice.simpleNote.model.UserModel;
import practice.simpleNote.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService usersService;

    public UserController(UserService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers() {
        List<UserModel> users = usersService.getAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(params="boardId")
    public ResponseEntity<List<UserModel>> filterUsersByBoard(@RequestParam(name = "boardId") String boardId) throws ResponseStatusException {
        List<UserModel> userModels = usersService.filterUsersByBoard(boardId);

        return new ResponseEntity<>(userModels, HttpStatus.OK);
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserModel> getUser(@PathVariable String userId) throws ResponseStatusException {
        UserModel userModel = usersService.getUserModel(userId);

        return new ResponseEntity<>(userModel, HttpStatus.OK);

    }

    //TODO refactor to use a DTO
    // also why tf are we sending an id to a create ????
    @ExceptionHandler({ResponseStatusException.class})
    @PostMapping("/createUser")
    public ResponseEntity<UserModel> createUser(@RequestBody @Valid UserModel user) throws Exception {

        return new ResponseEntity<>(usersService.createUser(user), HttpStatus.OK);
    }


    //TODO refactor to use a DTO
    @PutMapping("/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable("id") String userId,
                                                @RequestBody @Valid UserModel userModel) throws UserNotFoundException {
        return new ResponseEntity<UserModel>(usersService.updateUserModel(userModel,userId),HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") String id) throws Exception {

        return new ResponseEntity<>(usersService.deleteUserEntity(id));

    }

}
