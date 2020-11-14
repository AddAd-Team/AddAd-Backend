package addad.api.domain.payload.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PostResponse {

    private Long postId;

    private String title;

    private List<String> hashtag;

    private String description;

    private List<String> image;

    private String price;

    private Integer postTime;

    private Integer deadline;

}
