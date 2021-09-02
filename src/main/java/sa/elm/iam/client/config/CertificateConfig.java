package sa.elm.iam.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sa.elm.iam.client.config.security.certificate.PublicCertificate;
import sa.elm.iam.client.model.CertificateProperty;
import sa.elm.iam.client.config.security.certificate.Certificate;
import sa.elm.iam.client.config.security.certificate.PrivateCertificate;

import java.security.KeyStore;

@Configuration
public class CertificateConfig {

    @Bean
    public Certificate clientPrivateCertificate(KeyStore keyStore, CertificateProperty clientPrivateCertificateProperty){
        return new PrivateCertificate(keyStore, clientPrivateCertificateProperty);
    }

    @Bean
    public Certificate clientPublicCertificate(KeyStore keyStore, CertificateProperty clientPrivateCertificateProperty){
        return new PublicCertificate(keyStore, clientPrivateCertificateProperty);
    }
}
