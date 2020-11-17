package addad.api.domain.payload.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ModifyPost {

    private String title;

    private String hashtag;

    private String description;

    private MultipartFile Image;

    private String price;

    private String postTime;

    private String deadline;
}
