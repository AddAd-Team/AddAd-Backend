package addad.api.controller;

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

//    @PutMapping("/profileImg")
//    public void profileImgChange(@RequestHeader("X-Access-Token") String token, @RequestParam("file") ProfileImgChange.Request file) {
//
//    }
}
