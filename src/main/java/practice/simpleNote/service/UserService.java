package practice.simpleNote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import practice.simpleNote.Constants.Constants;
import practice.simpleNote.customExceptions.UserNotFoundException;
import practice.simpleNote.entity.UserEntity;
import practice.simpleNote.model.UserModel;
import practice.simpleNote.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    /**
     * As  of Spring 4.3, classes with a single constructor can omit the @Autowired annotation. A nice little bit of
     * convenience and boilerplate removal!
     * ALSO SEE http://olivergierke.de/2013/11/why-field-injection-is-evil/
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<UserModel> getAllUsers() {

        return userRepository.findAll().stream().map(this::toModel).collect(Collectors.toList());
    }


    public UserModel createUser(UserModel userModel)
    {
        return toModel(userRepository.save(fromModel(userModel)));
    }

    public UserModel getUserModel(String userId) {
        return toModel(this.getUserEntity(userId));
    }

    public HttpStatus deleteUserEntity(String userId) {

        Optional<UserEntity> optionalUsersEntity = userRepository.findById(userId);

        if (optionalUsersEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, Constants.UserNotFound);
        }

        userRepository.deleteById(userId);
        return HttpStatus.NO_CONTENT;

    }

    public UserModel updateUserModel(UserModel receivedModel, String userId) throws UserNotFoundException {
        Optional<UserEntity> userData = userRepository.findById(userId);

        if (userData.isPresent()) {
            UserEntity userEntity = userData.get();
            updateEntity(receivedModel, userEntity);

            return toModel(userEntity);
        } else {
            throw new UserNotFoundException();
        }
    }

    public List<UserModel> filterUsersByBoard(String boardId) {
        List<UserEntity> boards = userRepository.findByBoardsId(boardId);

        return boards.stream().map(this::toModel).collect(Collectors.toList());
    }

    private UserEntity getUserEntity(String userId) throws ResponseStatusException {
        Optional<UserEntity> userEntity = userRepository.findById(userId);

        return userEntity.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, Constants.UserNotFound));
    }

    private UserModel toModel(UserEntity userEntity) {
        return new UserModel(userEntity.getId(), userEntity.getUsername());
    }

    private UserEntity fromModel(UserModel model) {
        return new UserEntity(model.getUsername());
    }

    private void updateEntity(UserModel receivedModel, UserEntity userEntity) {
        userEntity.setUsername(receivedModel.getUsername());
        userRepository.save(userEntity);
    }

}
