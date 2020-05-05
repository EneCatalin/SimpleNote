package practice.simpleNote.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import practice.simpleNote.model.BoardModel;
import practice.simpleNote.service.BoardService;

import java.util.List;

//DID NOT MAKE A CREATE ROUTE. Boards belong to users and should not exist with no user attached so
// I kept it a user action. If such a route were to be added a simple copy paste should do the trick

@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping()
    public ResponseEntity<List<BoardModel>> getAllBoards() throws ResponseStatusException {
        List<BoardModel> boardModel = boardService.getAllBoards();

        return new ResponseEntity<>(boardModel, HttpStatus.OK);
    }

    @GetMapping(params="userId")
    public ResponseEntity<List<BoardModel>> filterBoardsByUser(@RequestParam(name = "userId") String userId) throws ResponseStatusException {
        List<BoardModel> boardModels = boardService.filterBoardsByUserId(userId);

        return new ResponseEntity<>(boardModels, HttpStatus.OK);

    }

    @GetMapping(path = "/{boardId}")
    public ResponseEntity<BoardModel> getBoard(@PathVariable String boardId) throws ResponseStatusException {

        BoardModel boardModel = boardService.getBoardModel(boardId);

        return new ResponseEntity<>(boardModel, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBoard(@PathVariable("id") String id) {
        return new ResponseEntity<>(boardService.deleteBoardEntity(id));
    }

//TODO add put mapping
//
//    @PutMapping("/{id}")
//    public ResponseEntity<BoardModel> updateBoard(@PathVariable("id") String boardId,
//                                                  @RequestBody @Valid BoardModel boardModel) throws BoardNotFoundException {
//
//        return new ResponseEntity<BoardModel>(boardService.updateBoardModel(boardModel, boardId), HttpStatus.OK);
//    }


}
