package addad.api.service.auth;

import addad.api.domain.payload.request.SignIn;
import addad.api.domain.payload.response.TokenResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    TokenResponse login(SignIn signIn);
    TokenResponse refreshToken(String token);
}
