package addad.api.domain.repository;

import addad.api.domain.entities.Likes;
import addad.api.domain.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Likes, Long> {
    Likes findByPostId(Long postId);
}
