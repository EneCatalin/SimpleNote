package practice.simpleNote.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import practice.simpleNote.Constants.Constants;
import practice.simpleNote.entity.BoardEntity;
import practice.simpleNote.entity.NoteEntity;
import practice.simpleNote.model.BoardModel;
import practice.simpleNote.model.NoteModel;
import practice.simpleNote.repository.BoardRepository;
import practice.simpleNote.repository.NoteRepository;

import java.util.Optional;
//TODO refactor this shit class
@Service
public class BoardActionsService {

    private final BoardRepository boardRepository;
    private final NoteRepository noteRepository;
    //TODO ask if this really belongs here or I should EITHER
    // A: move the methods i am importing to a separate class (what would it be called ???)
    // B: rewrite the methods I am importing here (this seems kind of a waste ???)
    private final NoteActions noteActions;
    private final BoardService boardActions;



    public BoardActionsService(BoardRepository boardRepository, NoteRepository noteRepository, NoteActions noteActions, BoardService boardActions) {
        this.boardRepository = boardRepository;
        this.noteRepository = noteRepository;
        this.noteActions = noteActions;
        this.boardActions = boardActions;
    }


    public NoteModel addNote(String boardId, String noteTitle,String noteContent){
        NoteEntity note = createNote(noteTitle,noteContent,boardId);

        return noteEntityToModel(note);

    }


    private NoteModel noteEntityToModel(NoteEntity noteEntity) {
        return new NoteModel(noteEntity.getId(), noteEntity.getTitle(),noteEntity.getContent(),noteEntity.getBoardId());
    }

    private NoteEntity createNote(String noteTitle, String noteContent, String boardId){
        return noteRepository.save(new NoteEntity(noteTitle,noteContent, boardId));
    }

    private BoardEntity getBoardEntity(String boardId) throws ResponseStatusException {
        Optional<BoardEntity> boardEntity = boardRepository.findById(boardId);

        return boardEntity.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, Constants.BoardNotFound));
    }


    public BoardModel removeNote (String boardId, String noteId){


        //TODO this won't do, move the function to BoardActions, delete from there and return the status
        noteActions.deleteNoteEntity(noteId);

        return boardActions.getBoardModel(boardId);
    }

    //TODO maybe create an empty the board function ?


}
