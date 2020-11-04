package addad.api.domain.repository;


import addad.api.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String userEmail);
    Optional<User> findByEmailAndAndRefreshToken(String Email, String refreshToken);
}
