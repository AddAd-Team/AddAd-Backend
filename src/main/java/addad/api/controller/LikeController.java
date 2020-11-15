package addad.api.controller;


import addad.api.service.like.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/like")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{postId}")
    public void feedLike(@PathVariable Long postId){
        likeService.feedLike(postId);
    }

    @DeleteMapping("/{postId}")
    public void feedUnlike(@PathVariable Long postId){
        likeService.feedUnlike(postId);
    }


}
