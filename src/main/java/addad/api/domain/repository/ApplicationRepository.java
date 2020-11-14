package addad.api.domain.repository;

import addad.api.domain.entities.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findAllByUser_id(Long user_id);
    Optional<Application> findByUser_idAndAndPost_id(Long user_id, Long post_id);
}