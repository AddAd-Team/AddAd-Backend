package addad.api.service.user;

import addad.api.domain.entities.EmailVerification;
import addad.api.domain.entities.enums.EmailVerificationStatus;
import addad.api.domain.payload.request.VerifyCodeRequest;
import addad.api.domain.repository.EmailVerificationRepository;
import addad.api.exception.InvalidAuthEmailException;
import addad.api.exception.InvalidAuthCodeException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmailVerificationRepository verificationRepository;

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String adminEmail;

    @Override
    public void sendEmail(String email) {
        String code = randomCode();
        this.sendEmail(email, code);

        verificationRepository.save(
                EmailVerification.builder()
                        .email(email)
                        .code(code)
                        .status(EmailVerificationStatus.UNVERIFIED)
                        .build()
        );
    }

    @Override
    public void verifyEmail(VerifyCodeRequest verifyCodeRequest) {
        String email = verifyCodeRequest.getEmail();
        String code = verifyCodeRequest.getCode();

        EmailVerification emailVerification = verificationRepository.findById(email)
                .orElseThrow(InvalidAuthEmailException::new);

        if (!emailVerification.getCode().equals(code))
            throw new InvalidAuthCodeException();

        verificationRepository.save(emailVerification.verify());
    }

    @Async
    void sendEmail(String email, String code) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(adminEmail);
        mailMessage.setTo(email);
        mailMessage.setSubject("혼치페이 이메일 인증입니다.");
        mailMessage.setText("계정 인증을 위한 코드는 " + code + "입니다.");
        javaMailSender.send(mailMessage);
    }

    private String randomCode() {
        String[] codes = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
                "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
                "Y", "Z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};

        Random random = new Random(System.currentTimeMillis());
        int tableLength = codes.length;
        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < 6; i++) {
            buf.append(codes[random.nextInt(tableLength)]);
        }

        return buf.toString();
    }
}