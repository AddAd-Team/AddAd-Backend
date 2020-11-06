package addad.api.exception;


import addad.api.error.Exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import addad.api.error.Exception.ErrorCode;

public class IncorrectPasswordException extends BusinessException {

    public IncorrectPasswordException() {
        super(ErrorCode.INVALID_PASSWORD);
    }
}
