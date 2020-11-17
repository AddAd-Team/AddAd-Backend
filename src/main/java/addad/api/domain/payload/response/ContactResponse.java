package addad.api.domain.payload.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ContactResponse {

    private String title;

    private Long creatorId;

    private String creatorName;

    private String creatorProfileImage;

    private Long advertiserId;

    private String advertiserName;

    private String advertiserProfileImage;
}
