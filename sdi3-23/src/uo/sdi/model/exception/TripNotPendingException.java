package uo.sdi.model.exception;

public class TripNotPendingException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = -5912702292852059862L;

    public TripNotPendingException() {
        super();
    }

    public TripNotPendingException(String message, Throwable cause) {
        super(message, cause);
    }

    public TripNotPendingException(String message) {
        super(message);
    }

    public TripNotPendingException(Throwable cause) {
        super(cause);
    }
}
