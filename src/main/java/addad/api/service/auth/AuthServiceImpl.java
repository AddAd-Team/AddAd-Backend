package addad.api.service.auth;

import addad.api.config.security.JwtTokenProvider;
import addad.api.domain.entities.User;
import addad.api.domain.payload.request.SignIn;
import addad.api.domain.payload.response.TokenResponse;
import addad.api.domain.repository.UserRepository;
import addad.api.exception.InvalidTokenException;
import addad.api.exception.UserNotFoundException;
import addad.api.utils.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final JwtTokenProvider jwtTokenProvider;

    @Value("${auth.jwt.prefix}")
    private String prefix;

    @Override
    public TokenResponse login(SignIn signIn) {
        User user = userRepository.findByEmail(signIn.getEmail())
                .filter(data -> PasswordEncoder.checkPassword(data.getPassword(), signIn.getPassword()))
                .orElseThrow(UserNotFoundException::new);

        TokenResponse token = responseToken(user.getEmail());
        user.changeRefrehToken(token.getRefreshToken());

        return token;
    }

    @Override
    public TokenResponse refreshToken(String token) {
        if(!jwtTokenProvider.isRefreshToken(token)) throw new InvalidTokenException();

        return responseToken(jwtTokenProvider.getUserEmail(token));
    }

    private TokenResponse responseToken(String email) {
        return TokenResponse.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(email))
                .refreshToken(jwtTokenProvider.generateRefreshToken(email))
                .tokenType(prefix)
                .build();
    }
}
