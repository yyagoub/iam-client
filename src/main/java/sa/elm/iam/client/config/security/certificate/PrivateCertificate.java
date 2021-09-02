package sa.elm.iam.client.config.security.certificate;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import sa.elm.iam.client.config.JksConfig;
import sa.elm.iam.client.config.exception.InternalServerException;
import sa.elm.iam.client.model.CertificateProperty;

import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

@AllArgsConstructor
public class PrivateCertificate extends Certificate {

    private final KeyStore keyStore;
    private final CertificateProperty certificate;
    private final Logger logger = LoggerFactory.getLogger(PrivateCertificate.class);

    @Override
    public PrivateKey getKey() throws UnrecoverableEntryException, NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException {
        if (this.checkCertificateEnvironment()) return null;
        KeyStore.PasswordProtection passwordProtection = new KeyStore.PasswordProtection(this.certificate.getPassword().toCharArray());
        KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry) this.keyStore.getEntry(this.certificate.getAlias(), passwordProtection);
        return pkEntry.getPrivateKey();
    }

    private Boolean checkCertificateEnvironment(){
        if (keyStore == null) {
            logger.error(" ---> CAN NOT INITIALIZED CERTIFICATE FROM UNINITIALIZED KEY-STORE <--- ");
            logger.error("please check key store properties from application.properties");
            return true;
        }
        if (StringUtils.isEmpty(certificate.getAlias())){
            logger.error(" ---> PRIVATE-CERTIFICATE WAS NOT INITIALIZED! <--- ");
            logger.error("please check private certificate properties from application.properties : private certificates alias is missing");
            return true;
        }
        if (StringUtils.isEmpty(certificate.getPassword())){
            logger.error(" ---> PRIVATE-CERTIFICATE WAS NOT INITIALIZED! <--- ");
            logger.error("please check private certificate properties from application.properties : private certificates password is missing");
            return true;
        }
        return false;
    }
}
