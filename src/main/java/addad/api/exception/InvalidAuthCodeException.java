package addad.api.exception;


import addad.api.error.exception.BusinessException;
import addad.api.error.exception.ErrorCode;

public class InvalidAuthCodeException extends BusinessException {

    public InvalidAuthCodeException() {
            super(ErrorCode.INVALID_AUTH_CODE);
    }
}