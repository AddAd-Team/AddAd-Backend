package addad.api.domain.repository;

import addad.api.domain.entities.Post;
import addad.api.domain.entities.enums.PostStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByPostStatus(Pageable pageable, PostStatus postStatus);
    Optional<Post> findById(Long id);
    List<Post> findByUserIdAndPostStatusOrPostStatus(Long userId, PostStatus firstStatus, PostStatus secondStatus);
}
