package sa.elm.iam.client.security.jks;

import sa.elm.iam.client.model.JksProperty;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

public class KeyStoreManager {

    public KeyStore getKeyStore(JksProperty jks) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException {
        FileInputStream file = new FileInputStream(jks.getPath());
        KeyStore keyStore = KeyStore.getInstance(jks.getStoreType());
        keyStore.load(file, jks.getPass().toCharArray());
        return keyStore;
    }

    private FileInputStream getFileInputStream(String path) throws IllegalArgumentException{
        try{
            return new FileInputStream(path);
        }catch (FileNotFoundException e){
            e.printStackTrace();
            throw new IllegalArgumentException("Check file path");
        }
    }

    private KeyStore getKeyStore(String storeType){
        try {
            return KeyStore.getInstance(storeType);
        } catch (KeyStoreException e){
            e.printStackTrace();
            throw new IllegalArgumentException("Check key store type");
        }
    }

    private void loadKeyStore(KeyStore keyStore, FileInputStream file, String pass){
        try {
            keyStore.load(file, pass.toCharArray());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException certificateException) {
            certificateException.printStackTrace();
        }
    }
}
