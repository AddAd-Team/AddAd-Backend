package addad.api.domain.repository;


import addad.api.domain.entities.User;
import addad.api.domain.entities.enums.Userinfo;
import addad.api.domain.payload.response.ProfileResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String userEmail);
    Page<User> findAllByUserinfo(Userinfo userinfo, Pageable pageable);
    Page<User> findAllByUserinfoAndNameContains(Userinfo userinfo, String name, Pageable pageable);
    Page<User> findAllByUserinfoAndHashtagContains(Userinfo userinfo, String hashtag, Pageable pageable);
}
