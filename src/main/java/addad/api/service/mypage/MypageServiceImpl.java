package addad.api.service.mypage;

import addad.api.config.security.AuthDetails;
import addad.api.config.security.AuthenticationFacade;
import addad.api.config.security.JwtTokenProvider;
import addad.api.domain.entities.User;
import addad.api.domain.entities.enums.Userinfo;
import addad.api.domain.payload.request.ModifyProfile;
import addad.api.domain.payload.response.ProfileResponse;
import addad.api.domain.payload.response.TokenResponse;
import addad.api.domain.repository.UserRepository;
import addad.api.exception.IncorrectPasswordException;
import addad.api.exception.UserNotFoundException;
import addad.api.utils.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MypageServiceImpl implements MypageService {

    private final AuthenticationFacade authenticationFacade;
    private final S3Service s3Service;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public void passwordAuth (String Password) {
        userRepository.findByEmail(authenticationFacade.getUserEmail())
                .filter(data -> passwordEncoder.matches(Password, data.getPassword()))
                .orElseThrow(IncorrectPasswordException::new);
    }

    @Override
    public void passwordChange (String Password) {
        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        String password = passwordEncoder.encode(Password);
        user.passwordChange(password);

        userRepository.save(user);
    }

    @Override
    public ProfileResponse profile() {
        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        String image = user.getProfileImg();
        if (image == null && user.getUserinfo() == Userinfo.creator) {
            image = "https://addad.s3.ap-northeast-2.amazonaws.com/userImg/creator.jpg";
        } else if (image == null && user.getUserinfo() == Userinfo.advertiser) {
            image = "https://addad.s3.ap-northeast-2.amazonaws.com/userImg/%E1%84%80%E1%85%AA%E1%86%BC%E1%84%80%E1%85%A9%E1%84%8C%E1%85%AE111.jpg";
        }

        return ProfileResponse.builder()
                .email(authenticationFacade.getUserEmail())
                .name(user.getName())
                .profileImg(image)
                .hashtag(user.getHashtag())
                .description(user.getDescription())
                .build();
    }

    @Override
    public ProfileResponse ModifyProfile(ModifyProfile modifyProfile) throws IOException {
        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        if (user.getProfileImg() != null) {
            s3Service.profileDelete(user.getProfileImg());
        }

        User ChangedUser = user.ChangeProfile(s3Service.Upload(modifyProfile.getImage(), "userImg/"), modifyProfile);
        userRepository.save(user);

        return ProfileResponse.builder()
                .email(authenticationFacade.getUserEmail())
                .name(ChangedUser.getName())
                .profileImg(ChangedUser.getEmail())
                .hashtag(ChangedUser.getHashtag())
                .description(ChangedUser.getDescription())
                .build();
    }
}