package addad.api.domain.payload.request;


import addad.api.domain.entities.Image;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PostRequest {

    @NotBlank
    private Integer userId;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String hashtag;

    @NotBlank
    private Integer price;

    @NotBlank
    private String postTime;

    @NotBlank
    private String deadline;


}
