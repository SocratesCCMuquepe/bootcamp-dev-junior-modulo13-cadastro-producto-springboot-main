package ao.osti.product_backend.services.exceptions;

public class DatabasesException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DatabasesException(String message) {
        super(message);
    }

    public DatabasesException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
