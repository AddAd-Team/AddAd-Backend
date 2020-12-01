package addad.api.domain.payload.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DetailFeedResponse {
    private Long postId;

    private Long userId;

    private String title;

    private String postImg;

    private String profileImg;

    private String price;

    private String postTime;

    private String deadline;

    private String recruitmentClosing;

    private String adClosing;

    private String hashtag;

    private String description;

    private Boolean likes;

    private Boolean application;

}
