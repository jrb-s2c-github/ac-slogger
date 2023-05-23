package za.co.s2c.ac.slogger;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;

@Value
@Builder
public class EncryptionResponseBean {
    String cipherText;

    @ToString.Exclude
    String sessionId;
}
