package addad.api.service.post;

import addad.api.config.security.AuthenticationFacade;
import addad.api.domain.entities.Application;
import addad.api.domain.entities.Likes;
import addad.api.domain.entities.Post;
import addad.api.domain.entities.User;
import addad.api.domain.payload.request.PostRequest;
import addad.api.domain.payload.response.FeedResponse;
import addad.api.domain.repository.ApplicationRepository;
import addad.api.domain.repository.LikesRepository;
import addad.api.domain.repository.PostRepository;
import addad.api.domain.repository.UserRepository;
import addad.api.exception.UserNotFoundException;
import addad.api.utils.DefaultImg;
import addad.api.utils.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.imageio.stream.IIOByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final LikesRepository likesRepository;
    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final S3Service s3Service;
    private final AuthenticationFacade authenticationFacade;
    private final DefaultImg defaultImg;



    @SneakyThrows
    @Override
    public void write(PostRequest postRequest) {
        String imgUrl = s3Service.Upload(postRequest.getImage(), "post_img/");

        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        postRepository.save(
                Post.builder()
                        .title(postRequest.getTitle())
                        .user_id(user.getId())
                        .hashtag(postRequest.getHashtag())
                        .postImg(imgUrl)
                        .description(postRequest.getDescription())
                        .price(postRequest.getPrice())
                        .postTime(postRequest.getPostTime())
                        .deadline(postRequest.getDeadline())
                        .build()
        );
    }

    @Override
    public List<FeedResponse> getFeed(Pageable pageable){
        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        Page<Post> posts = postRepository.findAllBy(pageable);
        List<FeedResponse> feedResponses = new ArrayList<>();
        for (Post post : posts) {

            Optional<Likes> likes = likesRepository.findByUser_idAndAndPost_id(user.getId(), post.getId());
            Optional<Application> application = applicationRepository.findByUser_idAndAndPost_id(user.getId(), post.getId());

            feedResponses.add(
                    FeedResponse.builder()
                            .profileImg(defaultImg.basic(post.getUser().getProfileImg()))
                            .title(post.getTitle())
                            .postImg(post.getPostImg())
                            .price(post.getPrice())
                            .postTime(post.getPostTime())
                            .hashtag(post.getHashtag())
                            .likes(likes.isPresent())
                            .application(likes.isPresent())
                            .createdAt(post.getCreatedAt())
                            .build()
            );
        }


        return feedResponses;
    }

    @Override
    public void apply(Long Id) {
        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        applicationRepository.save(
                Application.builder()
                    .user_id(user.getId())
                    .post_id(Id)
                    .build()
        );
    }
}