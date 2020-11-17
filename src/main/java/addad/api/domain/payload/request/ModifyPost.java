package addad.api.domain.payload.request;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
public class ModifyPost {

    private String title;

    private String hashtag;

    private String description;

    private MultipartFile Image;

    private String price;

    private Integer postTime;

    private Integer deadline;
}
