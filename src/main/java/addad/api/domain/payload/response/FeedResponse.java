package addad.api.domain.payload.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FeedResponse {

    private String profileImg;

    private String title;

    private String postImg;

    private Integer price;

    private String postTime;

    private String hashtag;

    private LocalDateTime createdAt;
}