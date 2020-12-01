package addad.api.exception;

import addad.api.error.exception.BusinessException;
import addad.api.error.exception.ErrorCode;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class UserNotFoundException extends BusinessException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_LEADER);
    }
}