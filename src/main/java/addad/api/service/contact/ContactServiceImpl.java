package addad.api.service.contact;
;
import addad.api.domain.entities.Contact;
import addad.api.domain.entities.Post;
import addad.api.domain.entities.User;
import addad.api.domain.entities.enums.Userinfo;
import addad.api.domain.payload.response.ContactResponse;
import addad.api.domain.repository.ContactRepository;
import addad.api.domain.repository.PostRepository;
import addad.api.domain.repository.UserRepository;
import addad.api.exception.PostNotFoundException;
import addad.api.exception.UserNotFoundException;
import addad.api.utils.DefaultImg;
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
    private final DefaultImg defaultImg;

    @Override
    public List<ContactResponse> getContact(Pageable pageable) {
        Page<Contact> contacts = contactRepository.findAllBy(pageable);
        List<ContactResponse> contactResponses = new ArrayList<>();
        for (Contact contact : contacts) {
            contactResponses.add(
                    ContactResponse.builder()
                            .title(contact.getTitle())
                            .advertiserId(contact.getAdvertiserId())
                            .advertiserName(contact.getCreatorName())
                            .advertiserProfileImage(defaultImg.userinfo(contact.getAdvertiserProfileImage(), Userinfo.advertiser))
                            .creatorId(contact.getCreatorId())
                            .creatorName(contact.getCreatorName())
                            .creatorProfileImage(defaultImg.userinfo(contact.getCreatorProfileImage(), Userinfo.creator))
                            .build()
            );
        }

        return contactResponses;
    }
}
