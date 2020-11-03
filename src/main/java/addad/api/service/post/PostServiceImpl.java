package addad.api.service.post;

import addad.api.domain.entities.Post;
import addad.api.domain.payload.request.PostRequest;
import addad.api.domain.payload.response.PostResponse;
import addad.api.domain.repository.LikeRepository;
import addad.api.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;


    @SneakyThrows
    @Override
    public void write(PostRequest postRequest) {
        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        Post post = postRepository.save(
                Post.builder()
                        .title(postRequest.getTitle())
                        .hashtag(postRequest.getHashtag())
                        .description(postRequest.getDescription())
                        .price(postRequest.getPrice())
                        .postTime(postRequest.getPostTime())
                        .deadline(postRequest.getDeadline())
                        .build()
        );

    }




}
