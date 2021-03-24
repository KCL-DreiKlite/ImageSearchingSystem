package priv.kcl.iss.core;


public class ISSException extends RuntimeException {
    /** Serial version UID */
    private static final long serialVersionUID = -7322946965232396697L;

    ISSException(String message) {
        super(message, null);
    }
    ISSException(String message, Throwable cause) {
        super(message, cause);
    }
    ISSException(Throwable cause) {
        super(cause.getMessage(), cause);
    }
}
