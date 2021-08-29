package sa.elm.iam.client.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sa.elm.iam.client.security.certificate.Certificate;
import sa.elm.iam.client.security.signature.CertificateSignature;
import sa.elm.iam.client.security.signature.PrivateCertificateSignature;

import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

@Configuration
@AllArgsConstructor
public class SignatureConfig {

    @Bean
    public PrivateCertificateSignature clientSignature(Certificate clientCertificate) throws UnrecoverableEntryException, CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
        return new CertificateSignature(clientCertificate.getKey());
    }
/*
    @Bean
    public PublicCertificateSignature authorizationSignature(JksProperty authorizationJksProperty) throws UnrecoverableEntryException, CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
        CertificateKeyStore certificate = new CertificateKeyStore(authorizationJksProperty);
        return new CertificateSignature(certificate.getKey());
    }
/*
    @Bean
    public PublicCertificateSignature idpSignature(JksProperty idpJksProperty) throws UnrecoverableEntryException, CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
        CertificateKeyStore certificate = new PublicCertificateKeyStore(idpJksProperty);
        return new CertificateSignature(certificate.getKey());
    }

 */
}
