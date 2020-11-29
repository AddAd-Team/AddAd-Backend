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
        firebaseCloudMessageService.sendMessageTo("cjm2AYIUTfijnbbYPbu8uL:APA91bFNx0C8nxdNgXYyzNdPozr_jGhsqalkLB4ZIXl_8_-u-u2HNUj0RJIuw9dXOuMqSQiKDORg8WdZqbP7ObSjd0zmSyYxQmgjs6_fe4qnURFAXznjCv6lHguQvrwUFEYNCkXT1tCP",
                "윤석준 병신", "ㄹㅇㅋㅋ");
    }
}
