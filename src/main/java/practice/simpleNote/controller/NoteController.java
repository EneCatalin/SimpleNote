package practice.simpleNote.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import practice.simpleNote.customExceptions.NoteNotFoundException;
import practice.simpleNote.dto.NoteDTO;
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
        List<NoteModel> notes = noteService.getAllNotes();

        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @GetMapping(path = "/{noteId}")
    public ResponseEntity<NoteModel> getNote(@PathVariable String noteId) throws ResponseStatusException {
        NoteModel noteModel = noteService.getNoteModel(noteId);

        return new ResponseEntity<>(noteModel, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteModel> updateNote(@PathVariable("id") String noteId,
                                                @RequestBody @Valid NoteDTO noteDto) throws NoteNotFoundException {

        return new ResponseEntity<>(noteService.updateNoteModel(noteId,noteDto),HttpStatus.OK);
    }

    @PostMapping("/createNote")
    public ResponseEntity<NoteModel> createNote(@RequestBody @Valid NoteDTO note) throws ResponseStatusException {
        return new ResponseEntity<>(noteService.createNote(note), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteNote(@PathVariable("id") String id) throws Exception {

        return new ResponseEntity<>(noteService.deleteNoteEntity(id));

    }
}
