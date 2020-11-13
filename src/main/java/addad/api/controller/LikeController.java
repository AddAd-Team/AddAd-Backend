package addad.api.controller;


import addad.api.service.like.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{postId}/like")
    public void feedLike(@PathVariable Long postId){
        likeService.feedLike(postId);
    }

    @DeleteMapping("/{postId}/like")
    public void feedUnlike(@PathVariable Long postId){
        likeService.feedUnlike(postId);
    }


}
