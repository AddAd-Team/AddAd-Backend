package addad.api.service.user;

import addad.api.domain.payload.request.SignUp;

public interface UserService {

    void signUp(SignUp signUp);
    void test();
}
