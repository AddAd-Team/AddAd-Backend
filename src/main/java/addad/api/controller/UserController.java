package addad.api.controller;

import addad.api.domain.payload.request.SignUp;
import addad.api.domain.payload.request.VerifyCodeRequest;
import addad.api.domain.payload.request.userEmail;
import addad.api.service.user.EmailService;
import addad.api.service.user.UserService;
import addad.api.utils.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final EmailService emailService;
    private final S3Service s3Service;

    @PostMapping("/emailSender")
    public void emailSender(@RequestBody() @NotNull @Email userEmail email) {
        emailService.sendEmail(email.getEmail());
    }

    @PutMapping("/emailAuth")
    public void verifyEmail(@RequestBody @Valid VerifyCodeRequest verifyCodeRequest) {
        emailService.verifyEmail(verifyCodeRequest);
    }

    @PostMapping("/signup")
    public void signUp(@RequestBody @Valid SignUp signUp) {
        userService.signUp(signUp);
    }
}
