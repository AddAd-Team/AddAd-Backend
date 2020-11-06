package addad.api.domain.payload.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FeedResponse {

    private Integer postId;

    private String profileImg;

    private String title;

    private String postImg;

    private Integer price;

    private String postTime;

    private String hashtag;
}
