package addad.api.domain.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

@Getter
@Setter
public class SearchTag {

    @Valid
    private String tag;
}
