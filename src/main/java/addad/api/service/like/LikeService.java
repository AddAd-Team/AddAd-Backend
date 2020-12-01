package addad.api.service.like;

public interface LikeService {
    void feedLike(Long postId);
    void feedUnlike(Long postId);
}
