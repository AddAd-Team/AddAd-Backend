package addad.api.domain.payload.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ContactResponse {

    private String title;

    private Long userId;

    private String userName;

    private String userProfile;

    private Long advId;

    private String advName;

    private String advProfile;
}
