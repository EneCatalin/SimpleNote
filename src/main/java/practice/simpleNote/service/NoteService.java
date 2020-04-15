package practice.simpleNote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import practice.simpleNote.entity.NoteEntity;
import practice.simpleNote.entity.UserEntity;
import practice.simpleNote.model.UserModel;
import practice.simpleNote.repository.NoteRepository;
import practice.simpleNote.repository.UserRepository;

import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public NoteEntity getNoteEntity(String noteId) {

        Optional<NoteEntity> noteEntity = noteRepository.findById(noteId);


        return noteEntity.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Note Not Found"));

    }
}
