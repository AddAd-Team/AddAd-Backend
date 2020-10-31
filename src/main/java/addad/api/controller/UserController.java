//package addad.api.controller;
//
//import addad.api.service.user.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.validation.Valid;
//
//@RestController
//@RequestMapping("/api/user")
//@RequiredArgsConstructor
//public class UserController {
//    private final UserService userService;
//
//    @PostMapping(value = "/signup")
//    public void signIn(@RequestBody @Valid SignUp signUp) {
//        userService.signUp(signUp);
//    }
//}
