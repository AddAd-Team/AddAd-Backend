package addad.api.exception;


import addad.api.error.exception.BusinessException;
import addad.api.error.exception.ErrorCode;

public class IncorrectPasswordException extends BusinessException {

    public IncorrectPasswordException() {
        super(ErrorCode.INVALID_PASSWORD);
    }
}
