package practice.simpleNote.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import practice.simpleNote.customExceptions.NoteNotFoundException;
import practice.simpleNote.dto.DeleteNoteDTO;
import practice.simpleNote.dto.NoteDTO;
import practice.simpleNote.model.BoardModel;
import practice.simpleNote.model.NoteModel;
import practice.simpleNote.service.BoardActionsService;
import practice.simpleNote.service.BoardService;
import practice.simpleNote.service.NoteActions;

@RestController
@RequestMapping("/boardActions")
public class BoardActionsController {


    private final BoardActionsService boardActionsService;
    private final NoteActions noteActions;


    public BoardActionsController(BoardActionsService boardActionsService, NoteActions noteActions,
                                  BoardService boardService) {
        this.boardActionsService = boardActionsService;
        this.noteActions = noteActions;
    }

    @ExceptionHandler({ResponseStatusException.class})
    @PostMapping("/addNote")
    public ResponseEntity<NoteModel> addNote(@RequestBody NoteDTO noteDTO) throws Exception {

        NoteModel note =  boardActionsService.addNote(noteDTO.getBoardId(),noteDTO.getTitle(),
                noteDTO.getContent());

        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @PostMapping("/editNote/{noteId}")
    public ResponseEntity<NoteModel> editNote(@PathVariable("noteId") String noteId,@RequestBody NoteDTO noteDTO) throws NoteNotFoundException {
        return new ResponseEntity<>(noteActions.updateNote(noteId,noteDTO),HttpStatus.OK);

    }

    @ExceptionHandler({ResponseStatusException.class})
    @PostMapping("/deleteNote")
    public ResponseEntity<BoardModel> removeNote(@RequestBody DeleteNoteDTO deleteNoteDTO) throws Exception {

        return new ResponseEntity<>(boardActionsService.removeNote(deleteNoteDTO.getBoardId(), deleteNoteDTO.getNoteId()),
                HttpStatus.OK);
    }

}
