package addad.api.service.post;

import addad.api.config.security.AuthenticationFacade;
import addad.api.domain.entities.Post;
import addad.api.domain.entities.User;
import addad.api.domain.entities.enums.Userinfo;
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
        Page<Post> posts = postRepository.findAllBy(pageable);
        List<FeedResponse> feedResponses = new ArrayList<>();
        for (Post post : posts) {
            feedResponses.add(
                    FeedResponse.builder()
                            .profileImg(defaultImg(post.getUser().getProfileImg()))
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

    public String defaultImg(String image) {
        if (image == null) {
            image = "https://addad.s3.ap-northeast-2.amazonaws.com/userImg/%E1%84%80%E1%85%AA%E1%86%BC%E1%84%80%E1%85%A9%E1%84%8C%E1%85%AE111.jpg";
        }

        return image;
    }
}