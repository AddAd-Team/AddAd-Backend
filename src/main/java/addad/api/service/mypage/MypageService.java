package addad.api.service.mypage;

import addad.api.domain.payload.response.ProfileResponse;

public interface MypageService {
    void passwordAuth (String Password);
    void passwordChange (String Password);
    ProfileResponse profile ();
}
