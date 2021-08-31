package sa.elm.iam.client.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sa.elm.iam.client.model.CertificateProperty;
import sa.elm.iam.client.model.IamRequestUrl;
import sa.elm.iam.client.model.JksProperty;

@Configuration
public class PropertyConfig {

    @Bean
    @ConfigurationProperties(prefix = "iam.request.url")
    public IamRequestUrl iamRequestUrl(){
        return new IamRequestUrl();
    }

    @Bean
    @ConfigurationProperties(prefix = "jks.store")
    public JksProperty jksProperty(){
        return new JksProperty();
    }

    @Bean
    @ConfigurationProperties(prefix = "certificate.client.private")
    public CertificateProperty clientPrivateCertificateProperty(){
        return new CertificateProperty();
    }

}
