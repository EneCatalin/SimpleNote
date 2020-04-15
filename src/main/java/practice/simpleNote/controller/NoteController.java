package practice.simpleNote.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import practice.simpleNote.entity.NoteEntity;
import practice.simpleNote.service.NoteService;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping(path = "/{noteId}")
    public ResponseEntity<NoteEntity> getUser(@PathVariable String noteId) throws ResponseStatusException {
        NoteEntity noteEntity = noteService.getNoteEntity(noteId);

        return new ResponseEntity<>(noteEntity, HttpStatus.OK);

    }
}
