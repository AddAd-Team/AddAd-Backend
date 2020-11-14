package addad.api.controller;

import addad.api.domain.payload.request.SignIn;
import addad.api.domain.payload.response.ApplicationResponse;
import addad.api.domain.payload.response.SearchResponse;
import addad.api.domain.payload.response.TokenResponse;
import addad.api.service.application.ApplicationService;
import addad.api.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/application")
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;

    @GetMapping("basic")
    public List<ApplicationResponse> applicationApply() {
        return applicationService.application();
    }
}