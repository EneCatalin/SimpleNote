package practice.simpleNote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import practice.simpleNote.Constants.Constants;
import practice.simpleNote.customExceptions.BoardNotFoundException;
import practice.simpleNote.entity.BoardEntity;
import practice.simpleNote.model.BoardModel;
import practice.simpleNote.repository.BoardRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<BoardModel> getAllBoards() {

        return boardRepository.findAll().stream().map(this::toModel).collect(Collectors.toList());
    }

    public BoardModel getBoardModel(String boardId) {
        return toModel(this.getBoardEntity(boardId));
    }


    public BoardModel updateBoardModel(BoardModel receivedModel, String boardId) throws BoardNotFoundException {
        Optional<BoardEntity> boardData = boardRepository.findById(boardId);

        if (boardData.isPresent()) {
            return toModel(updateEntity(receivedModel));
        } else {
            throw new BoardNotFoundException();
        }
    }

    public BoardModel createBoard(BoardModel boardModel)
    {
        return toModel(boardRepository.save(fromModel(boardModel)));
    }

    public HttpStatus deleteBoardEntity(String boardId) {

        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(boardId);

        if (optionalBoardEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, Constants.BoardNotFound);
        }

        boardRepository.deleteById(boardId);
        return HttpStatus.NO_CONTENT;

    }

    private BoardModel toModel(BoardEntity boardEntity) {
        return new BoardModel(boardEntity.getId(),boardEntity.getTitle());
    }

    private BoardEntity getBoardEntity(String boardId) throws ResponseStatusException {
        Optional<BoardEntity> boardEntity = boardRepository.findById(boardId);

        return boardEntity.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, Constants.BoardNotFound));
    }


    //AFFECTS THE DATABASE BY USING REPOSITORY.GET!!!
    private BoardEntity updateEntity(BoardModel receivedModel) {
        return boardRepository.save(new BoardEntity(receivedModel.getId(),receivedModel.getTitle()));
    }

    private BoardEntity fromModel(BoardModel model) {
        return new BoardEntity(model.getId(),model.getTitle());
    }



}
