package uo.sdi.business.exception;

public class TripNotFoundException extends BusinessException {
    /**
     * 
     */
    private static final long serialVersionUID = -864196835747811570L;

    public TripNotFoundException() {
        super();
    }

    public TripNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TripNotFoundException(String message) {
        super(message);
    }

    public TripNotFoundException(Throwable cause) {
        super(cause);
    }
}
