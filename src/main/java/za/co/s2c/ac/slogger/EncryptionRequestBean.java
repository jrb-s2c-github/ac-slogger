package za.co.s2c.ac.slogger;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;

@Value
@Builder
public class EncryptionRequestBean {
    @ToString.Exclude
    String plain;

    String alg;
    String mode;

    String email;

    @ToString.Exclude
    String password;

    String accountId;

    String appId;

    @ToString.Exclude
    String sessionId;
}
