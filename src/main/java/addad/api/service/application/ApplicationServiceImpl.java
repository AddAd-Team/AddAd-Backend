package addad.api.service.application;

import addad.api.domain.entities.*;
import addad.api.domain.entities.enums.PostStatus;
import addad.api.domain.entities.enums.Userinfo;
import addad.api.domain.payload.request.UserList;
import addad.api.domain.payload.response.ApplicationResponse;
import addad.api.domain.payload.response.SearchResponse;
import addad.api.domain.repository.*;
import addad.api.exception.PostNotFoundException;
import addad.api.exception.UserNotFoundException;
import addad.api.utils.AsyncFunc;
import addad.api.utils.DefaultImg;
import addad.api.utils.FirebaseCloudMessageService;
import com.google.firebase.internal.FirebaseRequestInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import addad.api.config.security.AuthenticationFacade;
import software.amazon.ion.IonException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService{

    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;
    private final ContactRepository contactRepository;
    private final PostRepository postRepository;
    private final AuthenticationFacade authenticationFacade;
    private final FirebaseCloudMessageService firebaseCloudMessageService;
    private final AsyncFunc asyncFunc;
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
                            .profileImg(defaultImg.userinfo(application.getUser().getProfileImg(), application.getUser().getUserinfo()))
                            .deviceToken(application.getUser().getDeviceToken())
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
    public void applicationAllow(List<UserList> userList, Long postId) throws IOException {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        for (UserList user : userList) {
            applicationRepository.deleteByUser_id(user.getId());

            asyncFunc.contactSave(user.getId(), post, postId);
            asyncFunc.notificationLog(user.getId(), post, post.getTitle(),"광고에 선정되셨습니다. 자세한 내용은 광고 페이지를 참고해주세요.");

            firebaseCloudMessageService.sendMessageTo(
                    user.getDeviceToken(),
                    "Addad 알림",
                    post.getTitle() + "광고에 선정되셨습니다. 자세한 내용은 광고 페이지를 참고해주세요."
            );
        }

        Uncontacted(postId, post);
    }

    @Async
    public void Uncontacted(Long postId, Post post) throws IOException {

        List<Application> applications = applicationRepository.findAllByPost_id(postId);

        for (Application application : applications) {
            asyncFunc.notificationLog(application.getUser_id(), post, post.getTitle(),"광고에 선정되지 않았습니다. 다음 기회를 노려주세요.");
            firebaseCloudMessageService.sendMessageTo(
                    application.getUser().getDeviceToken(),
                    "Addad 알림",
                    application.getPost().getTitle() + "광고에 선정되지 않았습니다. 다음 기회를 노려주세요."
            );
        }

        applicationRepository.deleteAllByPost_id(postId);

        postRepository.save(post.ChangePostStatus(PostStatus.completion));
    }

    @Override
    public void applicationDelete(Long postId){
        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        Application application = applicationRepository.findByUser_idAndPost_id(user.getId(), postId);

        applicationRepository.delete(application);
    }
}
