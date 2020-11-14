package addad.api.domain.repository;

import addad.api.domain.entities.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    Likes findByUser_idAndAndPost_id(Long user_id, Long post_id);
}
