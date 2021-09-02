package sa.elm.iam.client.config.security.signature;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

public interface PrivateCertificateSignature {
    byte[] sign(String message) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, UnsupportedEncodingException;
}
