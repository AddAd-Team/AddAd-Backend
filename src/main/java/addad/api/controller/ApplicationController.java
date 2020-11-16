package addad.api.controller;

import addad.api.domain.payload.response.ApplicationResponse;
import addad.api.service.application.ApplicationService;
import addad.api.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/application")
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;

    @GetMapping("basic/{postId}")
    public List<ApplicationResponse> applicationApply(@PathVariable Long postId) {
        return applicationService.application(postId);
    }

    @PostMapping("/apply")
    public void apply(@PathVariable Long postId) {
        applicationService.apply(postId);
    }
}