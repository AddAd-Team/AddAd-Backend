package addad.api.service.like;

public interface LikeService {
    void feedLike(String token, Integer postId);
    void feedUnlike(String token, Integer postId);
}
