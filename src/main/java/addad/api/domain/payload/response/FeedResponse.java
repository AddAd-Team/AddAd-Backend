package addad.api.domain.payload.response;

import addad.api.domain.entities.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FeedResponse {

    private String profileImg;

    private String title;

    private String postImg;

    private String price;

    private String postTime;

    private String hashtag;

    private LocalDateTime createdAt;

    private User user;
}