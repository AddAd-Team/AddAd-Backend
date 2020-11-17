package addad.api.domain.payload.response;

import addad.api.domain.entities.enums.Userinfo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ADResponse {

    private Long postId;

    private String title;

    private String postImg;

    private String postTime;

    private String hashtag;
}
