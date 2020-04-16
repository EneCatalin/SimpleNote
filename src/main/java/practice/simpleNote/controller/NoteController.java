package practice.simpleNote.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import practice.simpleNote.customExceptions.NoteNotFoundException;
import practice.simpleNote.entity.NoteEntity;
import practice.simpleNote.model.NoteModel;
import practice.simpleNote.service.NoteService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public ResponseEntity<List<NoteModel>> getAllNotes() {
        List<NoteModel> users = noteService.getAllNotes();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(path = "/{noteId}")
    public ResponseEntity<NoteEntity> getNote(@PathVariable String noteId) throws ResponseStatusException {
        NoteEntity noteEntity = noteService.getNoteEntity(noteId);

        return new ResponseEntity<>(noteEntity, HttpStatus.OK);

    }

    //TODO ACTUALLY CREATE THIS METHOD
    //TODO UPDATE THE THROWN EXCEPTION FROM USER TO NOTE
    @PutMapping("/{id}")
    public ResponseEntity<NoteModel> updateNote(@PathVariable("id") String noteId,
                                                @RequestBody @Valid NoteModel noteModel) throws NoteNotFoundException {
        return new ResponseEntity<NoteModel>(noteService.updateNoteModel(noteModel,noteId),HttpStatus.OK);
    }

    @PostMapping("/createNote")
    public ResponseEntity<NoteModel> createNote(@RequestBody @Valid NoteModel note) throws ResponseStatusException {
        return new ResponseEntity<>(noteService.createNote(note), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteNote(@PathVariable("id") String id) throws Exception {

        return new ResponseEntity<>(noteService.deleteNoteEntity(id));

    }
}
