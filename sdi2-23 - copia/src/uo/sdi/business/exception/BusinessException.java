package uo.sdi.business.exception;

public class BusinessException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = -4491230283332570690L;

    public BusinessException() {
        super();
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

}
