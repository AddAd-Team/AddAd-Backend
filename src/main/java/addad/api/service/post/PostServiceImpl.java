package addad.api.service.post;

import addad.api.config.security.AuthenticationFacade;
import addad.api.domain.entities.Post;
import addad.api.domain.entities.User;
import addad.api.domain.payload.request.PostRequest;
import addad.api.domain.payload.response.DetailFeedResponse;
import addad.api.domain.payload.response.FeedResponse;
import addad.api.domain.repository.PostRepository;
import addad.api.domain.repository.UserRepository;
import addad.api.exception.PostNotFoundException;
import addad.api.exception.UserNotFoundException;
import addad.api.utils.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        String imgUrl = s3Service.Upload(postRequest.getImage(), "post_img/");

        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        postRepository.save(
                Post.builder()
                        .title(postRequest.getTitle())
                        .hashtag(postRequest.getHashtag())
                        .img(imgUrl)
                        .userId(user.getId())
                        .description(postRequest.getDescription())
                        .price(postRequest.getPrice())
                        .postTime(postRequest.getPostTime())
                        .deadline(postRequest.getDeadline())
                        .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                        .build()
        );
    }

    @Override
    public List<FeedResponse> getFeed(Pageable pageable){
        Page<Post> posts = postRepository.findAllBy(pageable);
        List<FeedResponse> feedResponses = new ArrayList<>();
        for (Post post : posts) {
            User user = userRepository.findById(post.getUserId())
                    .orElseThrow(UserNotFoundException::new);
            feedResponses.add(
                    FeedResponse.builder()
                            .postId(post.getId())
                            .userId(user.getId())
                            .profileImg(user.getProfileImg())
                            .title(post.getTitle())
                            .postImg(post.getImg())
                            .price(post.getPrice())
                            .postTime(post.getPostTime())
                            .hashtag(post.getHashtag())
                            .createdAt(post.getCreatedAt())
                            .build()
            );
        }

        return feedResponses;
    }

    @Override
    public DetailFeedResponse getDetailFeed(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);

        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        return DetailFeedResponse.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .postImg(post.getImg())
                .profileImg(user.getProfileImg())
                .price(post.getPrice())
                .postTime(post.getPostTime())
                .deadline(post.getDeadline())
                .hashtag(post.getHashtag())
                .description(post.getDescription())
                .build();
    }

    @Override
    public void deleteFeed(Long postId){
        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

    }

}