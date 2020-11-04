package addad.api.controller;

import addad.api.domain.payload.response.ProfileResponse;
import addad.api.service.mypage.MypageService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
public class MypageController {
    private final MypageService mypageService;

    @PostMapping(value = "/passwordAuth")
    public void passwordAuth(@RequestBody @Valid String password) {
        mypageService.passwordAuth(password);
    }

    @PutMapping(value = "/passwordChange")
    public void passwordChange(@RequestParam("password") @Valid String password) {
        mypageService.passwordChange(password);
    }

    @GetMapping(value = "/profile")
    public ProfileResponse profile() {
        return mypageService.profile();
    }
}
