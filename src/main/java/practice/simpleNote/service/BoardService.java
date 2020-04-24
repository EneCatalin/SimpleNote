package practice.simpleNote.service;

import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final NoteRepository noteRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository, NoteRepository noteRepository) {
        this.boardRepository = boardRepository;
        this.noteRepository = noteRepository;
    }

    public HttpStatus deleteBoardEntity(String boardId) {

        //WE CAN'T WE getBoardEntity because we need to return a custom http code for this operation
        Optional<BoardEntity> board = boardRepository.findById(boardId);

        if (board.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, Constants.NoteNotFound);
        }

        deleteNotesByBoard(board.get());

        boardRepository.deleteById(boardId);
        return HttpStatus.NO_CONTENT;

    }

    private void deleteNotesByBoard(BoardEntity board) {
        Set<NoteEntity> notes = noteRepository.findByboard(board);
        for (NoteEntity noteEntity : notes) {
            noteRepository.deleteById(noteEntity.getId());
        }
    }

    public List<BoardModel> getAllBoards(){
        return boardRepository.findAll().stream().map(this::boardEntityToModel).collect(Collectors.toList());
    }

    public BoardModel getBoardModel(String boardId) {
        //get board entity
        BoardEntity board = this.getBoardEntity(boardId);

        return boardEntityToModel(board);
    }

    private BoardModel boardEntityToModel(BoardEntity board){
        //get associated notes
        Set<NoteEntity> noteEntities = noteRepository.findByboard(new BoardEntity(board.getId()));

        //Convert the notes to noteModels
        Set<NoteModel> noteModels = noteEntitiesToModels(noteEntities);

        return new BoardModel(board.getId(),board.getTitle(),noteModels);
    }

    private Set<NoteModel> noteEntitiesToModels(Set<NoteEntity> noteEntities) {
        Set<NoteModel> noteModels = new HashSet<>();
        for (NoteEntity entity : noteEntities) {
            noteModels.add(new NoteModel(entity.getId(),entity.getTitle(),entity.getContent()));
        }

        return noteModels;
    }

    private BoardEntity getBoardEntity(String boardId) throws ResponseStatusException {
        Optional<BoardEntity> boardEntity = boardRepository.findById(boardId);

        return boardEntity.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, Constants.BoardNotFound));
    }


}
