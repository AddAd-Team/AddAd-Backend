package addad.api.service.contact;

import addad.api.domain.entities.Contact;
import addad.api.domain.payload.response.ContactResponse;
import addad.api.domain.repository.ContactRepository;
import addad.api.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private UserRepository userRepository;
    private ContactRepository contactRepository;

    @Override
    public List<ContactResponse> getContact(Pageable pageable) {
        Page<Contact> contacts = contactRepository.findAllBy(pageable);
        List<ContactResponse> contactResponses = new ArrayList<>();
        for (Contact contact : contacts) {
            contactResponses.add(
                    ContactResponse.builder()
                            .build()
            );
        }

        return contactResponses;
    }
}
