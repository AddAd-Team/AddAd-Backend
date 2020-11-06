package addad.api.controller;

import addad.api.domain.entities.Post;
import addad.api.domain.payload.request.PostRequest;
import addad.api.domain.payload.response.FeedResponse;
import addad.api.domain.payload.response.PostResponse;
import addad.api.domain.repository.PostRepository;
import addad.api.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/post")
@Transactional
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/write")
    public void write(@ModelAttribute @Valid PostRequest postRequest, @RequestParam("image") MultipartFile file) {
        postService.write(postRequest, file);
    }

    @GetMapping("/feed")
    public List<FeedResponse> getFeed(@PageableDefault(sort = {"createdAt"}, size = 3) Pageable pageable) {
        return postService.getFeed(pageable);
    }
}