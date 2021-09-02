package sa.elm.iam.client.config.security.certificate;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import sa.elm.iam.client.model.CertificateProperty;

import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

@AllArgsConstructor
public class PublicCertificate extends Certificate {

    private final KeyStore keyStore;
    private final CertificateProperty certificate;
    private final Logger logger = LoggerFactory.getLogger(PublicCertificate.class);

    @Override
    public PublicKey getKey() throws UnrecoverableEntryException, NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException {
        if (this.checkCertificateEnvironment()) return null;
        return this.keyStore.getCertificate(this.certificate.getAlias()).getPublicKey();
    }

    private Boolean checkCertificateEnvironment(){
        if (keyStore == null) {
            logger.error(" ---> CAN NOT INITIALIZED CERTIFICATE FROM UNINITIALIZED KEY-STORE <--- ");
            logger.error("please check key store properties from application.properties");
            return true;
        }
        if (StringUtils.isEmpty(certificate.getAlias())){
            logger.error(" ---> PUBLIC-CERTIFICATE WAS NOT INITIALIZED! <--- ");
            logger.error("please check public certificate properties from application.properties : public certificates alias is missing");
            return true;
        }
        return false;
    }
}
