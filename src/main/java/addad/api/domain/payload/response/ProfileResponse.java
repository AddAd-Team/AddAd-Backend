package addad.api.domain.payload.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfileResponse {
    private String name;

    private String description;

    private String hashtag;

    private String profileImg;
}