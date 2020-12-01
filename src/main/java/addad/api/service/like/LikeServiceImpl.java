package addad.api.service.like;

import addad.api.config.security.AuthenticationFacade;
import addad.api.domain.entities.Application;
import addad.api.domain.entities.Likes;
import addad.api.domain.entities.Post;
import addad.api.domain.entities.User;
import addad.api.domain.repository.LikesRepository;
import addad.api.domain.repository.PostRepository;
import addad.api.domain.repository.UserRepository;
import addad.api.exception.PostNotFoundException;
import addad.api.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final LikesRepository likesRepository;
    private final AuthenticationFacade authenticationFacade;

    @Override
    public void feedLike(Long postId) {
        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        Optional<Likes> likes = likesRepository.findByUser_idAndAndPost_id(user.getId(), postId);

        if(!likes.isPresent()){
            likesRepository.save(
                Likes.builder()
                        .post_id(post.getId())
                        .user_id(user.getId())
                        .build()
            );
        }
    }

    @Override
    public void feedUnlike(Long postId){
        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        Likes likes = likesRepository.findByUser_idAndPost_id(user.getId(), postId);

        likesRepository.delete(likes);
    }
}
