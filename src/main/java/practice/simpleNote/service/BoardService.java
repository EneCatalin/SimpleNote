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

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
