package addad.api.service.post;

import addad.api.config.security.AuthenticationFacade;
import addad.api.domain.payload.request.PostRequest;
import addad.api.domain.payload.response.FeedResponse;
import addad.api.domain.repository.PostRepository;
import addad.api.domain.repository.UserRepository;
import addad.api.exception.UserNotFoundException;
import addad.api.utils.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    //    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final S3Service s3Service;
    private final AuthenticationFacade authenticationFacade;


    @SneakyThrows
    @Override
    public void write(PostRequest postRequest) {
        String imgUrl = s3Service.upload(postRequest.getPostImg());

        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        Post post = postRepository.save(
                Post.builder()
                        .title(postRequest.getTitle())
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
        Page<Post> posts = postRepository.findAllBy(pageable);
        List<FeedResponse> feedResponses = new ArrayList<>();
        for (Post post : posts) {
            User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                    .orElseThrow(UserNotFoundException::new);
            feedResponses.add(
                    FeedResponse.builder()
                            .profileImg(user.getProfileImg())
                            .title(post.getTitle())
                            .postImg(post.getPostImg())
                            .price(post.getPrice())
                            .postTime(post.getPostTime())
                            .hashtag(post.getHashtag())
                            .createdAt(post.getCreatedAt())
                            .build()
            );
        }

        return feedResponses;
    }

}