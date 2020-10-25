package addad.api.domain.payload.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignIn {

    private String email;

    private String password;
}
