package addad.api.domain.payload.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplicationResponse {
    private long user_id;

    private String name;

    private String profileImg;
}
