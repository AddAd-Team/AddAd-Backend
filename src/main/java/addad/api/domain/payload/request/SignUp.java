package addad.api.domain.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
public class SignUp {
    @Email
    private String email;

    private String password;

    private String name;

    private String tag;
}
