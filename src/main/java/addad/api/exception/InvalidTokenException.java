package addad.api.exception;

import addad.api.error.exception.BusinessException;
import addad.api.error.exception.ErrorCode;

public class InvalidTokenException extends BusinessException {

    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }

}