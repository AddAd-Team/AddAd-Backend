package addad.api.service.post;

import addad.api.domain.entities.Post;
import addad.api.domain.entities.User;
import addad.api.domain.payload.request.PostRequest;
import addad.api.domain.repository.PostRepository;
import addad.api.domain.repository.UserRepository;
import addad.api.exception.UserNotFoundException;
import addad.api.utils.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    public void write(PostRequest postRequest, MultipartFile file) {
        System.out.println(file.getSize());

        String imgUrl = s3Service.Upload(file, "post_img/");

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
}
