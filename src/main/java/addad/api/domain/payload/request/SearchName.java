package addad.api.domain.payload.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import javax.validation.Valid;

@Getter
@Setter
public class SearchName {

    @Valid
    private String name;
}
