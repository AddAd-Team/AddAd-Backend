package addad.api.domain.repository;

import addad.api.domain.entities.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Likes, Integer> {
    Likes findByPostIdAndUserId(Integer likeId, Integer userId);
}
