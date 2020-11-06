package addad.api.exception;


import addad.api.error.Exception.BusinessException;
import addad.api.error.Exception.ErrorCode;

public class InvalidAuthCodeException extends BusinessException {

    public InvalidAuthCodeException() {
            super(ErrorCode.INVALID_AUTH_CODE);
    }
}