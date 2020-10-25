package addad.api.domain.repository;


import addad.api.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String userEmail);
    User findByEmailAndAndRefreshToken(String Email , String refreshToken);
}
