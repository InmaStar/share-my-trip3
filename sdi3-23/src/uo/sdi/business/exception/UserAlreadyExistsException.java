package uo.sdi.business.exception;

public class UserAlreadyExistsException extends BusinessException {
    /**
     * 
     */
    private static final long serialVersionUID = 4517759783526715539L;

    public UserAlreadyExistsException() {
        super();
    }

    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }

    public UserAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
