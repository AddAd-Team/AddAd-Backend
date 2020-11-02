package addad.api.service.user;

import addad.api.domain.entities.User;
import addad.api.domain.payload.request.SignUp;
import addad.api.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import addad.api.utils.PasswordEncoder;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public void signUp(SignUp signUp) {
        userRepository.save(
                User.builder()
                        .userEmail(signUp.getEmail())
                        .userPw(PasswordEncoder.encode(signUp.getPassword()))
                        .userName(signUp.getName())
                        .hashtag(signUp.getTag())
                        .build()
        );
    }
}
