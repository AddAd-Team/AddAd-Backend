package addad.api.service.user;

import addad.api.domain.payload.request.SignUp;

import java.io.IOException;

public interface UserService {

    void signUp(SignUp signUp);
    void test() throws IOException;
}
