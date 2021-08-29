package sa.elm.iam.client.util;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Component
@AllArgsConstructor
public class IamRequestUrlUtil {

    public String createNonce(){
        return UUID.randomUUID().toString();
    }

    public String createMaxAge(){
        return this.createMaxAge(1200) + "";
    }

    private long createMaxAge(long second){
        return System.currentTimeMillis() + (1000*second);
    }
}
