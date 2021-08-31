package sa.elm.iam.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sa.elm.iam.client.model.JksProperty;
import sa.elm.iam.client.security.jks.KeyStoreManager;

import java.security.KeyStore;

@Configuration
public class JksConfig {

    @Bean
    public KeyStoreManager keyStoreManager(){
        return new KeyStoreManager();
    }

    @Bean
    public KeyStore keyStore(KeyStoreManager keyStoreManager, JksProperty jksProperty) throws Exception {
        return keyStoreManager.getKeyStore(jksProperty);
    }
}
