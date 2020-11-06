package addad.api.exception;

import addad.api.error.Exception.BusinessException;
import addad.api.error.Exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserAlreadyExsitsException extends BusinessException {
    public UserAlreadyExsitsException() {
        super(ErrorCode.USER_ALREADY_EXISTS_EXCEPTION);
    }
}
