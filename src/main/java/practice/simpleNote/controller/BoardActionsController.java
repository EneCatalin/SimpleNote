package practice.simpleNote.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import practice.simpleNote.customExceptions.NoteNotFoundException;
import practice.simpleNote.dto.NoteDTO;
import practice.simpleNote.model.NoteModel;
import practice.simpleNote.service.BoardActionsService;
import practice.simpleNote.service.NoteService;

@RestController
@RequestMapping("/boardActions")
public class BoardActionsController {


    private final BoardActionsService boardActionsService;
    private final NoteService noteService;


    public BoardActionsController(BoardActionsService boardActionsService, NoteService noteService) {
        this.boardActionsService = boardActionsService;
        this.noteService = noteService;
    }

    @ExceptionHandler({ResponseStatusException.class})
    @PostMapping("/addNote")
    public ResponseEntity<NoteModel> addNote(@RequestBody NoteDTO noteDTO) throws Exception {

        NoteModel note =  boardActionsService.addNote(noteDTO.boardId,noteDTO.title,
                noteDTO.content);

        return new ResponseEntity<>(note, HttpStatus.OK);
    }

//    //TODO MAKE THIS FUNCTION RETURN A NOTEMODEL
    @PostMapping("/editNote/{noteId}")
    public ResponseEntity<NoteModel> editNote(@PathVariable("noteId") String noteId,@RequestBody NoteDTO noteDTO) throws NoteNotFoundException {
        return new ResponseEntity<>(noteService.updateNoteModel(noteId,noteDTO),HttpStatus.OK);

    }

    //TODO write this function
    @ExceptionHandler({ResponseStatusException.class})
    @PostMapping("/deleteNote")
    public ResponseEntity<String> removeNote(@RequestBody NoteDTO noteDTO) throws Exception {
        boardActionsService.removeNote(noteDTO.boardId,noteDTO.id);

        return new ResponseEntity<>("Temporary Ok Message", HttpStatus.OK);
    }
}
