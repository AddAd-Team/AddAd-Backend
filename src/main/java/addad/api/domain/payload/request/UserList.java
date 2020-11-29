package addad.api.domain.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserList {
    private Long id;
    private String deviceToken;
}
