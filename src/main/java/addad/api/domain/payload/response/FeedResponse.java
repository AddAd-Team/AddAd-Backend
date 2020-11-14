package addad.api.domain.payload.response;

import addad.api.domain.entities.User;
import com.sun.org.apache.xpath.internal.operations.Bool;
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

    private Boolean likes;

    private Boolean application;

    private LocalDateTime createdAt;
}