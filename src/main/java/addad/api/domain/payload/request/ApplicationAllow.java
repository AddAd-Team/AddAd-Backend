package addad.api.domain.payload.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationAllow {

    private List<UserList> userList;

    private Long postId;
}
