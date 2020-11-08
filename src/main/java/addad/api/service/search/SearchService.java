package addad.api.service.search;

import addad.api.domain.entities.User;
import addad.api.domain.payload.request.SearchName;
import addad.api.domain.payload.request.SearchTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SearchService {
    List<User> creatorSearchBasic(Pageable pageable);
    List<User> creatorSearchByName(Pageable pageable, SearchName searchName);
    List<User> creatorSearchByTag(Pageable pageable, SearchTag searchTag);
}
