package addad.api.domain.payload.request;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PostRequest {

    @NotBlank
    private String title;
//
//    @NotBlank
//    private MultipartFile postImg;

    @NotBlank
    private String description;

    @NotBlank
    private String hashtag;

    @NotBlank
    private String price;

    @NotBlank
    private String postTime;

    @NotBlank
    private String deadline;


}
