package za.co.s2c.ac.slogger;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.message.ParameterizedMessage;

public class TokenizingMessageFactory implements MessageFactory {
    String sessionId = null;
    EncryptionClient client;

    public TokenizingMessageFactory() {
//      TODO  Move the url into application.yaml
        String url = "https://localhost:8443";

        client = Feign.builder().
                encoder(new GsonEncoder()).decoder(new GsonDecoder()).
                target(EncryptionClient.class, "https://localhost:8443");
    }

    @Override
    public Message newMessage(Object message) {
        return null;
    }

    @Override
    public Message newMessage(String message) {
        String providerPassword = System.getProperty("cryptoProviderPassword");
        String providerUsername = System.getProperty("cryptoProviderUsername");

        EncryptionRequestBean request = EncryptionRequestBean.builder().email(providerUsername).
                password(providerPassword).appId("f2aac19d-b464-4c04-979e-af1937399f3b").alg("AES").mode("FPE").
                accountId("85542a03-1574-4e9a-a2ae-587ab16146dc").sessionId(sessionId).plain(message).build();
        EncryptionResponseBean response = client.encrypt(request);
        sessionId = response.getSessionId();
        return new ParameterizedMessage("######## " + response.getCipherText());
    }

    @Override
    public Message newMessage(String message, Object... params) {
        return null;
    }
}
