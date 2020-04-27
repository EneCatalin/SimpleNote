package practice.simpleNote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import practice.simpleNote.Constants.Constants;
import practice.simpleNote.customExceptions.NoteNotFoundException;
import practice.simpleNote.dto.NoteDTO;
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

    private NoteEntity getNoteEntity(String noteId) {

        Optional<NoteEntity> noteEntity = noteRepository.findById(noteId);


        return noteEntity.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, Constants.NoteNotFound));

    }

    public NoteModel createNote(NoteDTO noteDTO)
    {
        return toModel(noteRepository.save(fromDTO(noteDTO)));
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

    public NoteModel updateNoteModel(String noteId, NoteDTO noteDto) throws NoteNotFoundException {
        Optional<NoteEntity> noteData = noteRepository.findById(noteId);

        if (noteData.isPresent()) {
            return toModel(updateEntity(noteDto));
        } else {
            throw new NoteNotFoundException();
        }
    }

    public NoteModel getNoteModel(String noteId) {
        return toModel(this.getNoteEntity(noteId));
    }


    private NoteModel toModel(NoteEntity noteEntity) {
        System.out.println(noteEntity.toString());
        return new NoteModel(noteEntity.getId(), noteEntity.getTitle(), noteEntity.getContent(),noteEntity.getBoardId());
    }

    private NoteEntity fromDTO(NoteDTO noteDTO) {
        return new NoteEntity(noteDTO.title,noteDTO.content,noteDTO.boardId);
    }



    // AFFECTS THE DATABASE BY USING REPOSITORY.GET!!!
    private NoteEntity updateEntity(NoteDTO noteDTO) {
        System.out.println(noteDTO.toString());
       return noteRepository.save(new NoteEntity(noteDTO.id,noteDTO.title, noteDTO.content,
                noteDTO.boardId));
    }



}
