package addad.api.domain.repository;

import addad.api.domain.entities.Application;
import addad.api.domain.entities.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findAllByPost_id(Long post_id);
    Optional<Application> findByUser_idAndAndPost_id(Long user_id, Long post_id);
    Application findByUser_idAndPost_id(Long user_id, Long post_id);
    List<Application> findAllByUser_id(Long user_id);

    @Transactional
    void deleteByUser_id(Long userId);

    @Transactional
    void deleteAllByPost_id(Long id);
}