package addad.api.controller;

import addad.api.domain.payload.request.ModifyProfile;
import addad.api.domain.payload.response.ProfileResponse;
import addad.api.service.mypage.MypageService;
import addad.api.utils.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
public class MypageController {
    private final MypageService mypageService;
    private final S3Service s3Service;

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

    @PutMapping(value = "/modifyProfile")
    public ProfileResponse modifyProfile(ModifyProfile modifyProfile) throws IOException {
        return mypageService.ModifyProfile(modifyProfile);
    }
}
