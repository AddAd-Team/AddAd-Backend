package addad.api.utils;

import addad.api.domain.entities.User;
import addad.api.domain.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AsyncFunc {
    private final UserRepository userRepository;

    @Async
    public void saveDeviceToken(User user, String deviceToken) {
        userRepository.save(user.changeDeviceToken(deviceToken));

        return;
    }
}
