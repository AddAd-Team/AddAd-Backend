package addad.api.controller;

import addad.api.domain.payload.request.SignIn;
import addad.api.domain.payload.response.TokenResponse;
import addad.api.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signin")
    public TokenResponse signIn(@RequestBody SignIn signIn) {
        return authService.login(signIn);
    }

    @GetMapping("/refresh")
    public TokenResponse refreshToken(@RequestHeader("refreshToken") @NotNull String refreshToken) {
        return authService.refreshToken(refreshToken);
    }
}
