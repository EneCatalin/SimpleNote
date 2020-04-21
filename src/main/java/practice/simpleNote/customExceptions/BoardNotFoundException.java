package practice.simpleNote.customExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import practice.simpleNote.Constants.Constants;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = Constants.BoardNotFound)
public class BoardNotFoundException extends Exception {
}
