package addad.api.service.mypage;

import addad.api.domain.payload.request.ModifyProfile;
import addad.api.domain.payload.response.ProfileResponse;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.ion.IonException;

import java.io.IOException;

public interface MypageService {
    void passwordAuth (String Password);
    void passwordChange (String Password);
    ProfileResponse profile ();
    ProfileResponse ModifyProfile(ModifyProfile modifyProfile) throws IOException;
}
