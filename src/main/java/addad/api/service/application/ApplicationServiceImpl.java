package addad.api.service.application;

import addad.api.domain.entities.Application;
import addad.api.domain.entities.Likes;
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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService{

    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;
    private final AuthenticationFacade authenticationFacade;
    private final DefaultImg defaultImg;

    @Override
    public List<ApplicationResponse> application(Long post_id) {

        List<Application> applications = applicationRepository.findAllByPost_id(post_id);

        List<ApplicationResponse> userList = new ArrayList<>();
        for (Application application : applications) {
            userList.add(
                    ApplicationResponse.builder()
                            .user_id(application.getUser_id())
                            .name(application.getUser().getName())
                            .profileImg(defaultImg.basic(application.getUser().getProfileImg()))
                            .build()
            );
        }

        return userList;
    }

    @Override
    public void apply(Long Id) {
        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        Optional<Application> application = applicationRepository.findByUser_idAndAndPost_id(user.getId(), Id);

        if(!application.isPresent()) {
            applicationRepository.save(
                    Application.builder()
                            .user_id(user.getId())
                            .post_id(Id)
                            .build()
            );
        }
    }

    @Override
    public void applicationDelete(Long postId){
        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        Application application = applicationRepository.findByUser_idAndPost_id(user.getId(), postId);

        applicationRepository.delete(application);
    }
}
