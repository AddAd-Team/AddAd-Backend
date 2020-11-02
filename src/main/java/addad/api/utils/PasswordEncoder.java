package addad.api.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoder {

    public static String encode(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public static boolean checkPassword(String dbPassword, String password) {
        return new BCryptPasswordEncoder().matches(password, dbPassword);
    }
}