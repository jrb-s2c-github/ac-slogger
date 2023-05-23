package za.co.s2c.ac.slogger;

import feign.Headers;
import feign.RequestLine;

@Headers("Content-Type: application/json")
public interface EncryptionClient {

    @RequestLine("POST crypto/encrypt")
    public EncryptionResponseBean encrypt(EncryptionRequestBean request);

}
