package addad.api.service.mypage;

import addad.api.domain.entities.User;
import addad.api.domain.repository.UserRepository;
import addad.api.exception.IncorrectPasswordException;
import addad.api.exception.UserNotFoundException;
import addad.api.utils.PasswordEncoder;

public class MypageServiceImpl implements MypageService {

    private UserRepository userRepository;

    @Override
    public void passwordAuth (String Email, String Password) {
        userRepository.findByEmail(Email)
                .filter(data -> PasswordEncoder.checkPassword(data.getPassword(), Password))
                .orElseThrow(IncorrectPasswordException::new);
    }

    @Override
    public void passwordChange (String Email, String Password) {
        User user = userRepository.findByEmail(Email)
                .orElseThrow(UserNotFoundException::new);
                
        String password = PasswordEncoder.encode(Password);
        user.passwordChange(password);
    }
}
