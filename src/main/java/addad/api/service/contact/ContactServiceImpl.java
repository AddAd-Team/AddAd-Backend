package addad.api.service.contact;
;
import addad.api.domain.entities.Contact;
import addad.api.domain.entities.Post;
import addad.api.domain.entities.User;
import addad.api.domain.payload.response.ContactResponse;
import addad.api.domain.repository.ContactRepository;
import addad.api.domain.repository.PostRepository;
import addad.api.domain.repository.UserRepository;
import addad.api.exception.PostNotFoundException;
import addad.api.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;

    @Override
    public List<ContactResponse> getContact(Pageable pageable) {
        Page<Contact> contacts = contactRepository.findAllBy(pageable);
        List<ContactResponse> contactResponses = new ArrayList<>();
        for (Contact contact : contacts) {
            contactRepository.save(
                    Contact.builder()
                            .advertiserId(contact.getAdvertiser().getId())
                            .creatorId(contact.getCreator().getId())
                            .postId(contact.getPost().getId())
                            .build()
            );

            contactResponses.add(
                    ContactResponse.builder()
                            .title(contact.getPost().getTitle())
                            .advertiserId(contact.getAdvertiser().getId())
                            .advertiserName(contact.getAdvertiser().getName())
                            .advertiserProfileImage(contact.getAdvertiser().getProfileImg())
                            .creatorId(contact.getCreator().getId())
                            .creatorName(contact.getCreator().getName())
                            .creatorProfileImage(contact.getCreator().getProfileImg())
                            .build()
            );
        }

        return contactResponses;
    }
}
