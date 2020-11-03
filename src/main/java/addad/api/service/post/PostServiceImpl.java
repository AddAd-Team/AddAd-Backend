package addad.api.service.post;

import addad.api.domain.entities.Post;
import addad.api.domain.payload.request.PostRequest;
import addad.api.domain.repository.LikeRepository;
import addad.api.domain.repository.PostRepository;
import addad.api.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;


    @SneakyThrows
    @Override
    public void write(PostRequest postRequest) {

        Post post = postRepository.save(
                Post.builder()
                        .userId(postRequest.getUserId())
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
