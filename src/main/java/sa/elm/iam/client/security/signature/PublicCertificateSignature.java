package sa.elm.iam.client.security.signature;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

public interface PublicCertificateSignature {
    void verify(String message, byte[] signedMessage) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, UnsupportedEncodingException;
}
