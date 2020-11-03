package addad.api.controller;

import addad.api.config.security.JwtTokenProvider;
import addad.api.domain.payload.request.userEmail;
import addad.api.service.mypage.MypageService;
import addad.api.service.user.EmailService;
import addad.api.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
public class MypageController {
    MypageService mypageService;
    JwtTokenProvider jwtTokenProvider;

    @PostMapping(value = "/passwordAuth")
    public void PasswordAuth(@RequestHeader("X-Access-Token") String token, @RequestBody() @NotNull String password) {
        String email = jwtTokenProvider.getUserEmail(token);
        mypageService.passwordAuth(email, password);
    }

    @PutMapping(value = "/passwordChange")
    public void PasswordChange(@RequestHeader("X-Access-Token") String token, @RequestBody() @NotNull String password) {
        String email = jwtTokenProvider.getUserEmail(token);
        mypageService.passwordChange(email, password);
    }
}
