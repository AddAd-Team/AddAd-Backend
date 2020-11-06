package addad.api.domain.entities;

import addad.api.domain.entities.enums.EmailVerificationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@Builder
@AllArgsConstructor
@RedisHash(timeToLive = 60 * 3)
public class EmailVerification {

    public static final Long MINUTE = 60L;

    @Id
    private String email;

    private String code;

    private EmailVerificationStatus status;

    @TimeToLive
    private Long ttl;

    public EmailVerification verify() {
        this.status = EmailVerificationStatus.VERIFIED;
        this.ttl = 3 * MINUTE;

        return this;
    }

    public boolean isVerified() {
        return status.equals(EmailVerificationStatus.VERIFIED);
    }
}
