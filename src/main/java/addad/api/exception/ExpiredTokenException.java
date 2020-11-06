package addad.api.exception;

import addad.api.error.Exception.BusinessException;
import addad.api.error.Exception.ErrorCode;

public class ExpiredTokenException extends BusinessException {
    public ExpiredTokenException() {
        super(ErrorCode.EXPIRED_TOKEN);
    }

}
