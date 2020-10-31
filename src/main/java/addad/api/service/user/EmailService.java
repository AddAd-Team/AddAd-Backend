package addad.api.service.user;

import addad.api.domain.payload.request.VerifyCodeRequest;

public interface EmailService {

    void sendEmail(String email);
    void verifyEmail(VerifyCodeRequest verifyCodeRequest);
}