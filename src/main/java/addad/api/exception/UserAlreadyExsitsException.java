package addad.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserAlreadyExsitsException extends RuntimeException {
    public UserAlreadyExsitsException() {
        super("User already exsits");
    }
}
