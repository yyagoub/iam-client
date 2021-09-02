package sa.elm.iam.client.config.exception;

public class UnauthorizedException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public UnauthorizedException() {
        super("UNAUTHORIZED");
    }

}