package addad.api.service.mypage;

import addad.api.domain.payload.request.ModifyPost;
import addad.api.domain.payload.request.ModifyProfile;
import addad.api.domain.payload.response.ADResponse;
import addad.api.domain.payload.response.PostResponse;
import addad.api.domain.payload.response.ProfileResponse;

import java.io.IOException;
import java.util.List;

public interface MypageService {
    void passwordAuth (String Password);
    void passwordChange (String Password);
    ProfileResponse profile ();
    ProfileResponse ModifyProfile(ModifyProfile modifyProfile) throws IOException;
    PostResponse ModifyPost(ModifyPost modifyPost, Long postId) throws IOException;
    List<ADResponse> ADList();
    List<ADListResponse> likeAd();
}
