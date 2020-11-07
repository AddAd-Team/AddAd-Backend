package addad.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Integer> {
    Like findByPostIdAndUserId(Integer likeId, Integer userId);
}
