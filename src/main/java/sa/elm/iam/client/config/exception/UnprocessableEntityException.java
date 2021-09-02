package sa.elm.iam.client.config.exception;

import java.util.List;

public class UnprocessableEntityException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    List<ValidationError> errors;

    public UnprocessableEntityException(String message){
        super(message);
    }

    public UnprocessableEntityException(List<ValidationError> errors) {
        super("UNPROCESSABLE_ENTITY");
        this.errors = errors;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }

    public void setErrors(List<ValidationError> errors) {
        this.errors = errors;
    }

}