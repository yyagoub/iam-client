package sa.elm.iam.client.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import sa.elm.iam.client.model.JksProperty;
import sa.elm.iam.client.config.security.jks.KeyStoreManager;

import java.security.KeyStore;

@Configuration
public class JksConfig {

    private final Logger logger = LoggerFactory.getLogger(JksConfig.class);

    @Bean
    public KeyStoreManager keyStoreManager(){
        return new KeyStoreManager();
    }

    @Bean
    public KeyStore keyStore(KeyStoreManager keyStoreManager, JksProperty jksProperty) throws Exception {
        if (StringUtils.isEmpty(jksProperty.getPath()) || StringUtils.isEmpty(jksProperty.getStoreType())) {
            logger.error(" ---> KEY-STORE WAS NOT INITIALIZED! <--- ");
            logger.error("please check key store properties from application.properties");
            return keyStoreManager.getKeyStore();
        }
        return keyStoreManager.getKeyStore(jksProperty);
    }
}
