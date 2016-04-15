package uo.sdi.business.exception;

public class InvalidActionException extends BusinessException {
    /**
     * 
     */
    private static final long serialVersionUID = -9011569667507669929L;

    public InvalidActionException() {
        super();
    }

    public InvalidActionException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidActionException(String message) {
        super(message);
    }

    public InvalidActionException(Throwable cause) {
        super(cause);
    }
}
