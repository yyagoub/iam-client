package sa.elm.iam.client.security.certificate;

import lombok.AllArgsConstructor;
import sa.elm.iam.client.model.CertificateProperty;

import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

@AllArgsConstructor
public class PublicCertificate extends Certificate{

    private final KeyStore keyStore;
    private final CertificateProperty certificate;

    @Override
    public PublicKey getKey() throws UnrecoverableEntryException, NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException {
        return this.keyStore.getCertificate(this.certificate.getAlias()).getPublicKey();
    }
}
