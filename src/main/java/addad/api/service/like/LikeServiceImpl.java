package addad.api.service.like;

import addad.api.domain.repository.LikeRepository;
import addad.api.domain.repository.PostRepository;
import addad.api.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private UserRepository userRepository;
    private PostRepository postRepository;
    private LikeRepository likeRepository;

    @Override
    public void feedLike(String token, Integer postId) {

    }

    @Override
    public void feedUnlike(String token, Integer postId){

    }
}
