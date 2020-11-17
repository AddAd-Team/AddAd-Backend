package addad.api.domain.repository;

import addad.api.domain.entities.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByUser_idAndAndPost_id(Long user_id, Long post_id);
    Likes findByUser_idAndPost_id(Long user_id, Long post_id);
}
