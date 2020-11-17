package addad.api.domain.payload.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PostResponse {

    private Long postId;

    private String title;

    private String hashtag;

    private String description;

    private String image;

    private String price;

    private String postTime;

    private String deadline;

}
