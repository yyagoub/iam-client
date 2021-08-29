package sa.elm.iam.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sa.elm.iam.client.model.CertificateProperty;
import sa.elm.iam.client.security.certificate.Certificate;
import sa.elm.iam.client.security.certificate.PrivateCertificate;

import java.security.KeyStore;

@Configuration
public class CertificateConfig {

    @Bean
    public Certificate clientCertificate(KeyStore clientKeyStore, CertificateProperty clientCertificateProperty){
        return new PrivateCertificate(clientKeyStore, clientCertificateProperty);
    }
}
