package practice.simpleNote.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import practice.simpleNote.customExceptions.BoardNotFoundException;
import practice.simpleNote.model.BoardModel;
import practice.simpleNote.service.BoardService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    public ResponseEntity<List<BoardModel>> getAllNotes() {
        List<BoardModel> boards = boardService.getAllBoards();

        return new ResponseEntity<>(boards, HttpStatus.OK);
    }


    @GetMapping(path = "/{boardId)")
    public ResponseEntity<BoardModel> getNote(@PathVariable String boardId) throws ResponseStatusException {
        BoardModel boardModel = boardService.getBoardModel(boardId);

        return new ResponseEntity<>(boardModel, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardModel> updateBoard(@PathVariable("id") String boardId,
                                                @RequestBody @Valid BoardModel boardModel) throws BoardNotFoundException {

        return new ResponseEntity<BoardModel>(boardService.updateBoardModel(boardModel,boardId),HttpStatus.OK);
    }

    @PostMapping("/createBoard")
    public ResponseEntity<BoardModel> createBoard(@RequestBody @Valid BoardModel board) throws ResponseStatusException {
        return new ResponseEntity<>(boardService.createBoard(board), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBoard(@PathVariable("id") String id) {

        return new ResponseEntity<>(boardService.deleteBoardEntity(id));

    }


}
