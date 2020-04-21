package practice.simpleNote.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import practice.simpleNote.dto.NoteDTO;
import practice.simpleNote.model.NoteModel;
import practice.simpleNote.service.BoardActionsService;

@RestController
@RequestMapping("/boardActions")
public class BoardActionsController {


    private final BoardActionsService boardActionsService;

    public BoardActionsController(BoardActionsService boardActionsService) {
        this.boardActionsService = boardActionsService;
    }

    @ExceptionHandler({ResponseStatusException.class})
    @PostMapping("/addNote")
    public ResponseEntity<NoteModel> addNote(@RequestBody NoteDTO noteDTO) throws Exception {
        NoteModel note =  boardActionsService.addNote(noteDTO.boardId,noteDTO.noteId,noteDTO.noteContent,
                noteDTO.noteContent);

        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    //TODO MAKE THIS FUNCTION RETURN A NOTEMODEL
    @ExceptionHandler({ResponseStatusException.class})
    @PostMapping("/editNote")
    public ResponseEntity<String> editNote(@RequestBody NoteDTO noteDTO) throws Exception {
        boardActionsService.editNote(noteDTO.boardId,noteDTO.noteId, noteDTO.noteContent);

        return new ResponseEntity<>("Temporary Ok Message", HttpStatus.OK);
    }

    //TODO write this function
    @ExceptionHandler({ResponseStatusException.class})
    @PostMapping("/deleteNote")
    public ResponseEntity<String> removeNote(@RequestBody NoteDTO noteDTO) throws Exception {
        boardActionsService.removeNote(noteDTO.boardId,noteDTO.noteId);

        return new ResponseEntity<>("Temporary Ok Message", HttpStatus.OK);
    }
}
