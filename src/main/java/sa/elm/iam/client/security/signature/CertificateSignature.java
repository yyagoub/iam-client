package sa.elm.iam.client.security.signature;

import lombok.AllArgsConstructor;

import java.io.UnsupportedEncodingException;
import java.security.*;

@AllArgsConstructor
public class CertificateSignature implements PublicCertificateSignature, PrivateCertificateSignature {

    private final Key key;

    @Override
    public byte[] sign(String message) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, UnsupportedEncodingException {
        return this.getSignature(message).sign();
    }

    @Override
    public void verify(String message, byte[] signedMessage) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, UnsupportedEncodingException {
        this.getSignature(message).verify(signedMessage);
    }

    private Signature getSignature(String message) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, UnsupportedEncodingException {
        Signature signature = Signature.getInstance("SHA256withRSA");
        this.prepareSignature(signature, this.key);
        signature.update(message.getBytes("UTF8"));
        return signature;
    }

    private void prepareSignature(Signature signature, Key key) throws InvalidKeyException {
        if (key instanceof PublicKey)
            signature.initVerify((PublicKey) key);
        else if (key instanceof PrivateKey)
            signature.initSign((PrivateKey) key);
        else
            throw new IllegalArgumentException("unknown key instance");
    }

}
