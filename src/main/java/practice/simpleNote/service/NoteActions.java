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
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NoteActions {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteActions(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }


    public Set<NoteEntity> filterNotesByBoardId(String boardId) {

        //Hibernate generates some weird query by default so i wrote my own saner version that
        //won't crash the project
        //TODO check out wtf it does by default ???
        return noteRepository.findByboardId(boardId);
    }

    public void deleteNotes(Set<NoteEntity> notes){

        for (NoteEntity noteEntity : notes) {
            noteRepository.delete(noteEntity);
        }

    }


//    private NoteEntity getNoteEntity(String noteId) {
//
//        Optional<NoteEntity> noteEntity = noteRepository.findById(noteId);
//
//
//        return noteEntity.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, Constants.NoteNotFound));
//
//    }

//    public NoteModel createNote(NoteDTO noteDTO)
//    {
//        return entityToModel(noteRepository.save(entityFromDTO(noteDTO)));
//    }


    public void deleteNoteEntity(String noteId) {

        Optional<NoteEntity> optionalNoteEntity = noteRepository.findById(noteId);

        if (optionalNoteEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, Constants.NoteNotFound);
        }

        noteRepository.deleteById(noteId);

    }

    public List<NoteModel> getAllNotes() {

        return noteRepository.findAll().stream().map(this::entityToModel).collect(Collectors.toList());
    }

    public NoteModel updateNote(String noteId, NoteDTO noteDto) throws NoteNotFoundException {
        Optional<NoteEntity> noteData = noteRepository.findById(noteId);

        if (noteData.isPresent()) {
            return entityToModel(updateEntityFromDTO(noteDto));
        } else {
            throw new NoteNotFoundException();
        }
    }

//    public NoteModel getNoteModel(String noteId) {
//        return entityToModel(this.getNoteEntity(noteId));
//    }


    private NoteModel entityToModel(NoteEntity noteEntity) {
        return new NoteModel(noteEntity.getId(), noteEntity.getTitle(), noteEntity.getContent(),noteEntity.getBoardId());
    }

    private NoteEntity entityFromDTO(NoteDTO noteDTO) {
        return new NoteEntity(noteDTO.getTitle(),noteDTO.getContent(),noteDTO.getBoardId());
    }



    // AFFECTS THE DATABASE BY USING REPOSITORY.GET!!!
    private NoteEntity updateEntityFromDTO(NoteDTO noteDTO) {
       return noteRepository.save(new NoteEntity(noteDTO.getId(),noteDTO.getTitle(), noteDTO.getContent(),
                noteDTO.getBoardId()));
    }



}
