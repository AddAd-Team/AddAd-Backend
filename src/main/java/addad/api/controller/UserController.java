package addad.api.controller;

import addad.api.domain.payload.request.SignUp;
import addad.api.domain.payload.request.VerifyCodeRequest;
import addad.api.domain.payload.request.userEmail;
import addad.api.service.user.EmailService;
import addad.api.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final EmailService emailService;

    @PostMapping(value = "/emailSender")
    public void EmailSender(@RequestBody() @NotNull @Email userEmail email) {
        emailService.sendEmail(email.getEmail());
    }

    @PutMapping(value = "/emailAuth")
    public void verifyEmail(@RequestBody @Valid VerifyCodeRequest verifyCodeRequest) {
        emailService.verifyEmail(verifyCodeRequest);
    }

    @PostMapping(value = "/signup")
    public void SignUp(@RequestBody @Valid SignUp signUp) {
        userService.signUp(signUp);
    }
}
