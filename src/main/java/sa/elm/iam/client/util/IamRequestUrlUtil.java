package sa.elm.iam.client.util;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Component
@AllArgsConstructor
public class IamRequestUrlUtil {

    private final ZoneId zoneId = ZoneId.of("GMT+03:00");

    public String createNonce(){
        return UUID.randomUUID().toString();
    }

    public String createMaxAge(){
        return this.getCurrentTimestamp() + "";
    }

    private long getTimestamp(long second){
        return this.getCurrentTimestamp() + (1000*second);
    }

    private long getCurrentTimestamp(){
        return ZonedDateTime.now(zoneId).toInstant().toEpochMilli();
    }
}
