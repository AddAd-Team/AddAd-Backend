package addad.api.service.user;

import addad.api.domain.entities.User;
import addad.api.domain.entities.enums.Userinfo;
import addad.api.domain.payload.request.SignUp;
import addad.api.domain.repository.UserRepository;
import addad.api.utils.FirebaseCloudMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final FirebaseCloudMessageService firebaseCloudMessageService;

    @Override
    public void signUp(SignUp signUp) {
        userRepository.save(
                User.builder()
                        .userEmail(signUp.getEmail())
                        .userPw(passwordEncoder.encode(signUp.getPassword()))
                        .name(signUp.getName())
                        .hashtag(signUp.getTag())
                        .userinfo(Userinfo.valueOf(signUp.getUserinfo()))
                        .profileImg("")
                        .build()
        );
    }

    @Override
    public void test() throws IOException {
        firebaseCloudMessageService.sendMessageTo("ccxgY2bHTVibR2icqakNU_:APA91bHXo3tZiZU_QG6GUTU-V5jrVZ_8_0SYjzYQV9t3l5wLVnJ77yEZ84hFX25GiYgGydlR_jM60Tvh-2P8G-2k9ZRi1QWZYFI6_Bly54ZTM0Qpr9frWWXoNNNBrSGmxVkgaZ4LTjIm",
                "윤석준 병신", "ㄹㅇㅋㅋ");
    }
}
