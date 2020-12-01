package addad.api.exception;


import addad.api.error.exception.BusinessException;
import addad.api.error.exception.ErrorCode;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class InvalidAuthEmailException extends BusinessException {

    public InvalidAuthEmailException() {
        super(ErrorCode.INVALID_AUTH_EMAIL);
    }
}