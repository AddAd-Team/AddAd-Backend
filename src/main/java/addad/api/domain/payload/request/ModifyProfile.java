package addad.api.domain.payload.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ModifyProfile {
    @NotNull
    private MultipartFile Image;

    private String name;

    private String description;

    private String hashtag;
}
