package addad.api.domain.payload.response;

import addad.api.domain.entities.enums.Userinfo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TokenResponse {

    private String accessToken;

    private String refreshToken;

    private String tokenType;

    private Userinfo userinfo;
}

