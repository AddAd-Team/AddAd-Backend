package addad.api.exception;


import addad.api.error.Exception.BusinessException;
import addad.api.error.Exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class InvalidAuthEmailException extends BusinessException {

    public InvalidAuthEmailException() {
        super(ErrorCode.INVALID_AUTH_EMAIL);
    }
}