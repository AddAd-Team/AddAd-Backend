package addad.api.service.contact;

import addad.api.domain.payload.response.ContactResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContactService {
    List<ContactResponse> getContact(Pageable pageable);
}
