package addad.api.controller;

import addad.api.domain.entities.User;
import addad.api.domain.payload.request.*;
import addad.api.service.search.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

    @GetMapping(value = "/basic")
    public List<User> creatorSearchBasic(@PageableDefault(sort = {"createdAt"}, size = 10) Pageable pageable) {
        return searchService.creatorSearchBasic(pageable);
    }

    @GetMapping(value = "/name")
    public List<User> creatorSearchByName(@PageableDefault(sort = {"createdAt"}, size = 10) Pageable pageable, SearchName searchName) {
        return searchService.creatorSearchByName(pageable, searchName);
    }

    @GetMapping(value = "/tag")
    public List<User> creatorSearchByTag(@PageableDefault(sort = {"createdAt"}, size = 10) Pageable pageable, SearchTag searchTag) {
        return searchService.creatorSearchByTag(pageable, searchTag);
    }
}
