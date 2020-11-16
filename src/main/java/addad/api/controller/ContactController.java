package addad.api.controller;

import addad.api.domain.payload.response.ContactResponse;
import addad.api.service.contact.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @GetMapping
    public List<ContactResponse> getContact(@PageableDefault(sort = {"id"}, size = 10) Pageable pageable) {
        return contactService.getContact(pageable);
    }
}
