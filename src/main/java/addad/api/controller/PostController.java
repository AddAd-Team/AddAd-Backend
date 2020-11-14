package addad.api.controller;

import addad.api.domain.payload.request.PostRequest;
import addad.api.domain.payload.response.DetailFeedResponse;
import addad.api.domain.payload.response.FeedResponse;
import addad.api.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/post")
@Transactional
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping(value = "/write")
    public void write(PostRequest postRequest) {
        postService.write(postRequest);
    }

    @GetMapping("/feed")
    public List<FeedResponse> getFeed(@PageableDefault(sort = {"createdAt"}, size = 10) Pageable pageable) {
        return postService.getFeed(pageable);
    }

    @PostMapping("/apply")
    public void apply(@RequestParam("id") @Valid Long Id) {
        postService.apply(Id);
      
    @GetMapping("/feed/{id}")
    public DetailFeedResponse detailFeed(@PathVariable Long id){
        return postService.getDetailFeed(id);
    }
}
