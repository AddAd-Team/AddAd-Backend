package addad.api.controller;

import addad.api.domain.payload.request.ApplicationAllow;
import addad.api.domain.payload.response.ApplicationResponse;
import addad.api.service.application.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
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

    @PostMapping("/apply/{postId}")
    public void apply(@PathVariable Long postId) {
        applicationService.apply(postId);
    }

    @PostMapping("/allow")
    public void allow(@RequestBody @Valid ApplicationAllow applicationAllow) throws IOException {
        applicationService.applicationAllow(applicationAllow.getUserList(), applicationAllow.getPostId());
    }

    @DeleteMapping("/apply/{postId}")
    public void applicationDelete(@PathVariable Long postId) {
        applicationService.applicationDelete(postId);
    }
}