package addad.api.controller;

import addad.api.domain.payload.request.ModifyPost;
import addad.api.domain.payload.request.ModifyProfile;
import addad.api.domain.payload.response.ADListResponse;
import addad.api.domain.payload.response.ADResponse;
import addad.api.domain.payload.response.PostResponse;
import addad.api.domain.payload.response.ProfileResponse;
import addad.api.service.mypage.MypageService;
import addad.api.utils.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
public class MypageController {
    private final MypageService mypageService;
    private final S3Service s3Service;

    @PostMapping("/passwordAuth")
    public void passwordAuth(@RequestBody @Valid String password) {
        mypageService.passwordAuth(password);
    }

    @PutMapping("/passwordChange")
    public void passwordChange(@RequestParam("password") @Valid String password) {
        mypageService.passwordChange(password);
    }

    @GetMapping("/profile")
    public ProfileResponse profile() {
        return mypageService.profile();
    }

    @PutMapping("/modifyProfile")
    public ProfileResponse modifyProfile(ModifyProfile modifyProfile) throws IOException {
        return mypageService.ModifyProfile(modifyProfile);
    }

    @PutMapping(value = "/{postId}")
    public PostResponse modifyPost(ModifyPost modifyPost, @PathVariable Long postId) throws IOException {
        return mypageService.ModifyPost(modifyPost, postId);
    }

    @GetMapping(value = "/adlist")
    public List<ADResponse> adResponse(){
        return mypageService.ADList();
    }
  
    @GetMapping("/likeAd")
    public List<ADListResponse> likeAd() {
        return mypageService.likeAd();
    }
}
