package addad.api.service.user;

import addad.api.domain.entities.enums.Userinfo;
import addad.api.domain.payload.request.SignUp;
import addad.api.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;

    @Override
    public void signUp(SignUp signUp) {
        userRepository.save(
                User.builder()
                        .userEmail(signUp.getEmail())
                        .userPw(passwordEncoder.encode(signUp.getPassword()))
                        .userName(signUp.getName())
                        .hashtag(signUp.getTag())
                        .userinfo(Userinfo.valueOf(signUp.getUserinfo()))
                        .build()
        );
    }
}
