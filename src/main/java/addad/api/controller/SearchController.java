package addad.api.controller;

import addad.api.domain.entities.User;
import addad.api.domain.payload.request.*;
import addad.api.domain.payload.response.ProfileResponse;
import addad.api.domain.payload.response.SearchResponse;
import addad.api.service.search.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

    @GetMapping(value = "/basic")
    public List<SearchResponse> creatorSearchBasic(@PageableDefault(sort = {"createdAt"}, size = 10) Pageable pageable) {
        return searchService.creatorSearchBasic(pageable);
    }

    @GetMapping(value = "/name")
    public List<SearchResponse> creatorSearchByName(@PageableDefault(sort = {"createdAt"}, size = 10) Pageable pageable, SearchName searchName) {
        return searchService.creatorSearchByName(pageable, searchName);
    }

    @GetMapping(value = "/tag")
    public List<SearchResponse> creatorSearchByTag(@PageableDefault(sort = {"createdAt"}, size = 10) Pageable pageable, SearchTag searchTag) {
        return searchService.creatorSearchByTag(pageable, searchTag);
    }

    @GetMapping(value = "/user")
    public ProfileResponse findOneUserById(@RequestParam("id") @Valid Long Id) {
        return searchService.findOneUserById(Id);
    }
}
