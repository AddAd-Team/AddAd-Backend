package addad.api.domain.repository;

import addad.api.domain.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PostRepository extends JpaRepository<Post, Integer> {
    Page<Post> findAllBy(Pageable pageable);
}
