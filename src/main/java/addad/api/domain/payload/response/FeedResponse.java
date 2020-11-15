package addad.api.domain.payload.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FeedResponse {

    private Long postId;

    private Long userId;

    private String profileImg;

    private String title;

    private String postImg;

    private String price;

    private String postTime;

    private String hashtag;

    private String createdAt;

    private Boolean isLike;
}