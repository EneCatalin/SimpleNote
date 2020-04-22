package practice.simpleNote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import practice.simpleNote.Constants.Constants;
import practice.simpleNote.customExceptions.NoteNotFoundException;
import practice.simpleNote.entity.NoteEntity;
import practice.simpleNote.model.NoteModel;
import practice.simpleNote.repository.NoteRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public NoteEntity getNoteEntity(String noteId) {

        Optional<NoteEntity> noteEntity = noteRepository.findById(noteId);


        return noteEntity.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, Constants.NoteNotFound));

    }

    public NoteModel createNote(NoteModel noteModel)
    {
        return toModel(noteRepository.save(fromModel(noteModel)));
    }


    public HttpStatus deleteNoteEntity(String noteId) {

        Optional<NoteEntity> optionalNoteEntity = noteRepository.findById(noteId);

        if (optionalNoteEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, Constants.NoteNotFound);
        }

        noteRepository.deleteById(noteId);
        return HttpStatus.NO_CONTENT;

    }

    public List<NoteModel> getAllNotes() {

        return noteRepository.findAll().stream().map(this::toModel).collect(Collectors.toList());
    }

    public NoteModel updateNoteModel(NoteModel receivedModel, String noteId) throws NoteNotFoundException {
        Optional<NoteEntity> noteData = noteRepository.findById(noteId);

        if (noteData.isPresent()) {
            return toModel(updateEntity(receivedModel));
        } else {
            throw new NoteNotFoundException();
        }
    }

    public NoteModel getNoteModel(String noteId) {
        return toModel(this.getNoteEntity(noteId));
    }


    private NoteModel toModel(NoteEntity noteEntity) {
        return new NoteModel(noteEntity.getId(), noteEntity.getTitle(), noteEntity.getContent(),
                noteEntity.getBoardId());
    }

    private NoteEntity fromModel(NoteModel model) {
        return new NoteEntity(model.getId(),model.getTitle(),model.getContent(),model.getBoardId());
    }



    // AFFECTS THE DATABASE BY USING REPOSITORY.GET!!!
    private NoteEntity updateEntity(NoteModel receivedModel) {
       return noteRepository.save(new NoteEntity(receivedModel.getId(), receivedModel.getTitle(),
                receivedModel.getContent(),receivedModel.getBoardId()));
    }



}
