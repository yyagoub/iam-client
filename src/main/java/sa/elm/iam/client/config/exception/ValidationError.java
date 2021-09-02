package sa.elm.iam.client.config.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ValidationError {

    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    public ValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }

}