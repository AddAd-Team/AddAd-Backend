package addad.api.exception;

import addad.api.error.exception.BusinessException;
import addad.api.error.exception.ErrorCode;

public class ExpiredTokenException extends BusinessException {
    public ExpiredTokenException() {
        super(ErrorCode.EXPIRED_TOKEN);
    }

}
