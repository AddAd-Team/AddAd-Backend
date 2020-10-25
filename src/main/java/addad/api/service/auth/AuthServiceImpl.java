package addad.api.service.auth;

import addad.api.domain.entities.User;
import addad.api.domain.payload.request.SignIn;
import addad.api.domain.payload.response.TokenResponse;
import addad.api.domain.repository.UserRepository;
import addad.api.exception.InvalidTokenException;
import addad.api.exception.UserNotFoundException;
import addad.api.utils.JwtUtil;
import addad.api.utils.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public TokenResponse login(SignIn signIn) {
        User user = userRepository.findByEmail(signIn.getEmail())
                .filter(data -> PasswordEncoder.checkPassword(data.getPassword(), signIn.getPassword()))
                .orElseThrow(UserNotFoundException::new);

        TokenResponse token = new TokenResponse(user.getEmail());

        userRepository.save(user.changeRefrehToken(token.getRefreshToken()));
        return token;
    }

    @Override
    public TokenResponse refreshToken(String token) {
        String email = JwtUtil.parseToken(token);
        User user = userRepository.findByEmailAndAndRefreshToken(email, token);
        if(user == null || !user.getRefreshToken().equals(token)) throw new InvalidTokenException();

        TokenResponse newToken = new TokenResponse(email);
        userRepository.save(user.changeRefrehToken(newToken.getRefreshToken()));

        return newToken;
    }
}
