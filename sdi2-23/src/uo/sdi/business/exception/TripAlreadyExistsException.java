package uo.sdi.business.exception;

public class TripAlreadyExistsException extends BusinessException {
    /**
     * 
     */
    private static final long serialVersionUID = -7668217120623537578L;

    public TripAlreadyExistsException() {
        super();
    }

    public TripAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public TripAlreadyExistsException(String message) {
        super(message);
    }

    public TripAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
