package addad.api.service.user;

import addad.api.domain.entities.User;
import addad.api.domain.entities.enums.Userinfo;
import addad.api.domain.payload.request.SignUp;
import addad.api.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
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
    private  final PasswordEncoder passwordEncoder;

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
    public void test() {
        String strStartDate = "20210104";
        String strEndDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String strFormat = "yyyyMMdd";

        SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
        try{
            Date startDate = sdf.parse(strStartDate);
            Date endDate = sdf.parse(strEndDate);

            long diffDay = (startDate.getTime() - endDate.getTime()) / (24*60*60*1000);
            System.out.println(diffDay+"일");
        }catch(ParseException e){
            e.printStackTrace();
        }
    }
}
