package addad.api.service.search;

import addad.api.domain.entities.User;
import addad.api.domain.payload.request.SearchName;
import addad.api.domain.payload.request.SearchTag;
import addad.api.domain.payload.response.ProfileResponse;
import addad.api.domain.payload.response.SearchResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SearchService {
    List<SearchResponse> creatorSearchBasic(Pageable pageable);
    List<SearchResponse> creatorSearchByName(Pageable pageable, SearchName searchName);
    List<SearchResponse> creatorSearchByTag(Pageable pageable, SearchTag searchTag);
    ProfileResponse findOneUserById(Long Id);
}
