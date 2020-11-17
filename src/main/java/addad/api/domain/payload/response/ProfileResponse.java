package addad.api.domain.payload.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfileResponse {
    private long id;

    private String email;

    private String name;

    private String description;

    private String hashtag;

    private String profileImg;
}