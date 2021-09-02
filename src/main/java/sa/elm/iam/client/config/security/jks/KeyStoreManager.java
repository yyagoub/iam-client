package sa.elm.iam.client.config.security.jks;

import sa.elm.iam.client.model.JksProperty;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

public class KeyStoreManager {

    public KeyStore getKeyStore() throws KeyStoreException {
        return KeyStore.getInstance("JKS");
    }

    public KeyStore getKeyStore(JksProperty jks) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException {
        FileInputStream file = new FileInputStream(jks.getPath());
        KeyStore keyStore = KeyStore.getInstance(jks.getStoreType());
        keyStore.load(file, jks.getPass().toCharArray());
        return keyStore;
    }

}
