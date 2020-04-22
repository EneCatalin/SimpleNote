package practice.simpleNote.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import practice.simpleNote.model.BoardModel;
import practice.simpleNote.service.BoardService;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping()
    public ResponseEntity<List<BoardModel>> getAllBoards() throws ResponseStatusException{
        List<BoardModel> boardModel = boardService.getAllBoards();

        return new ResponseEntity<>(boardModel, HttpStatus.OK);
    }

    @GetMapping(path = "/{boardId}")
    public ResponseEntity<BoardModel> getBoard(@PathVariable String boardId) throws ResponseStatusException{
        BoardModel boardModel = boardService.getBoardModel(boardId);

        return new ResponseEntity<>(boardModel, HttpStatus.OK);
    }

//    @GetMapping(path = "/{boardId)")
//    public ResponseEntity<BoardModel> getNote(@PathVariable String boardId) throws ResponseStatusException {
//        BoardModel boardModel = boardService.getBoardModel(boardId);
//
//        return new ResponseEntity<>(boardModel, HttpStatus.OK);
//    }

//    @PutMapping("/{id}")
//    public ResponseEntity<BoardModel> updateBoard(@PathVariable("id") String boardId,
//                                                  @RequestBody @Valid BoardModel boardModel) throws BoardNotFoundException {
//
//        return new ResponseEntity<BoardModel>(boardService.updateBoardModel(boardModel, boardId), HttpStatus.OK);
//    }
//
//    @PostMapping("/createBoard")
//    public ResponseEntity<BoardModel> createBoard(@RequestBody @Valid BoardModel board) throws ResponseStatusException {
//        return new ResponseEntity<>(boardService.createBoard(board), HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<HttpStatus> deleteBoard(@PathVariable("id") String id) {
//        return new ResponseEntity<>(boardService.deleteBoardEntity(id));
//    }


}
