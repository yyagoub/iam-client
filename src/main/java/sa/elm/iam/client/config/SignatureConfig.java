package sa.elm.iam.client.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sa.elm.iam.client.config.security.certificate.Certificate;
import sa.elm.iam.client.config.security.signature.CertificateSignature;
import sa.elm.iam.client.config.security.signature.PrivateCertificateSignature;
import sa.elm.iam.client.config.security.signature.PublicCertificateSignature;

import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

@Configuration
@AllArgsConstructor
public class SignatureConfig {

    @Bean
    public PrivateCertificateSignature clientPrivateCertificateSignature(Certificate clientPrivateCertificate) throws UnrecoverableEntryException, CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
        return new CertificateSignature(clientPrivateCertificate.getKey());
    }

    @Bean
    public PublicCertificateSignature clientPublicCertificateSignature(Certificate clientPublicCertificate) throws UnrecoverableEntryException, CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
        return new CertificateSignature(clientPublicCertificate.getKey());
    }

}
