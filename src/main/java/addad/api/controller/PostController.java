package addad.api.controller;

import addad.api.domain.payload.request.PostRequest;
import addad.api.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public void write(@ModelAttribute @Valid PostRequest postRequest) { postService.write(postRequest); }



}
