package addad.api.service.like;

import addad.api.config.security.AuthenticationFacade;
import addad.api.domain.entities.Likes;
import addad.api.domain.entities.Post;
import addad.api.domain.entities.User;
import addad.api.domain.repository.LikeRepository;
import addad.api.domain.repository.PostRepository;
import addad.api.domain.repository.UserRepository;
import addad.api.exception.PostNotFoundException;
import addad.api.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    private final AuthenticationFacade authenticationFacade;

    @Override
    public void feedLike(Long postId) {
        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        Likes likes = likeRepository.findByPostId(post.getId());

        likeRepository.save(
                Likes.builder()
                        .postId(post.getId())
                        .userId(user.getId())
                        .build()
        );

    }

    @Override
    public void feedUnlike(Long postId){
        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        Likes likes = likeRepository.findByPostId(post.getId());

        likeRepository.delete(likes);
    }
}
