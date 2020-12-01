package addad.api.service.notification;

import addad.api.config.security.AuthenticationFacade;
import addad.api.domain.entities.Notification;
import addad.api.domain.entities.User;
import addad.api.domain.repository.NotificationRepository;
import addad.api.domain.repository.UserRepository;
import addad.api.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;
    private final AuthenticationFacade authenticationFacade;

    @Override
    public List<Notification> notificationList() {
        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        return notificationRepository.findAllByUserId(user.getId());
    }
}