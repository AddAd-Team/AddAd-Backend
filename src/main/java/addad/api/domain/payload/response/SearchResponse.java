package addad.api.domain.payload.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SearchResponse {
    private long id;

    private String name;

    private String profileImg;

    private String hashtag;
}
