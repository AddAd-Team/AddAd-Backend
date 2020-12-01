package addad.api.domain.repository;

import addad.api.domain.entities.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ContactRepository extends JpaRepository<Contact, Long> {
    Page<Contact> findAllBy(Pageable pageable);
    List<Contact> findAllByCreatorId(Long userId);
}
