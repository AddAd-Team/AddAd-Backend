package addad.api.service.application;

import addad.api.domain.entities.Application;
import addad.api.domain.entities.User;
import addad.api.domain.entities.enums.Userinfo;
import addad.api.domain.payload.response.ApplicationResponse;
import addad.api.domain.payload.response.SearchResponse;
import addad.api.domain.repository.ApplicationRepository;
import addad.api.domain.repository.UserRepository;
import addad.api.exception.UserNotFoundException;
import addad.api.utils.DefaultImg;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import addad.api.config.security.AuthenticationFacade;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService{

    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;
    private final AuthenticationFacade authenticationFacade;
    private final DefaultImg defaultImg;

    @Override
    public List<ApplicationResponse> application() {
        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        List<Application> applications = applicationRepository.findAllByUser_id(user.getId());

        List<ApplicationResponse> userList = new ArrayList<>();
        for (Application application : applications) {
            userList.add(
                    ApplicationResponse.builder()
                            .id(application.getId())
                            .name(application.getUser().getName())
                            .profileImg(defaultImg.basic(application.getUser().getProfileImg()))
                            .build()
            );
        }

        return userList;
    }
}
