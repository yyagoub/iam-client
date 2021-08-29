package sa.elm.iam.client.security.certificate;

import lombok.AllArgsConstructor;
import sa.elm.iam.client.model.CertificateProperty;

import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

@AllArgsConstructor
public class PrivateCertificate extends Certificate {

    private final KeyStore keyStore;
    private final CertificateProperty certificate;

    @Override
    public PrivateKey getKey() throws UnrecoverableEntryException, NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException {
        KeyStore.PasswordProtection passwordProtection = new KeyStore.PasswordProtection(this.certificate.getPassword().toCharArray());
        KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry) this.keyStore.getEntry(this.certificate.getAlias(), passwordProtection);
        return pkEntry.getPrivateKey();
    }
}
