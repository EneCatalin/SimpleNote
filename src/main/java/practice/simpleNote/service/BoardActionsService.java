package practice.simpleNote.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import practice.simpleNote.Constants.Constants;
import practice.simpleNote.entity.BoardEntity;
import practice.simpleNote.entity.NoteEntity;
import practice.simpleNote.model.NoteModel;
import practice.simpleNote.repository.BoardRepository;
import practice.simpleNote.repository.NoteRepository;

import java.util.Optional;

@Service
public class BoardActionsService {

    private final BoardRepository boardRepository;
    private final NoteRepository noteRepository;


    public BoardActionsService(BoardRepository boardRepository, NoteRepository noteRepository) {
        this.boardRepository = boardRepository;
        this.noteRepository = noteRepository;
    }


    public NoteModel addNote(String boardId, String noteId, String noteTitle,String noteContent){

        //check if there is a board with that id first
        BoardEntity board=getBoardEntity(boardId);

        //if a board exists then it makes sense to actually go ahead and create a note
        NoteEntity note = createNote(noteTitle,noteContent,boardId);

        return noteEntityToModel(note);

    }


    private NoteModel noteEntityToModel(NoteEntity noteEntity) {
        return new NoteModel(noteEntity.getId(), noteEntity.getTitle(),noteEntity.getContent(),noteEntity.getBoardId());
    }

    private NoteEntity createNote(String noteTitle, String noteContent, String boardId){
        //TODO fix this shit
        BoardEntity localBoard = new BoardEntity(boardId);
        return noteRepository.save(new NoteEntity(noteTitle,noteContent, localBoard));
    }

    private BoardEntity getBoardEntity(String boardId) throws ResponseStatusException {
        Optional<BoardEntity> boardEntity = boardRepository.findById(boardId);

        return boardEntity.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, Constants.BoardNotFound));
    }


    public void editNote(String boardId, String noteId, String noteContent){

    }

    public void removeNote(String boardId, String NoteId){

    }

}
