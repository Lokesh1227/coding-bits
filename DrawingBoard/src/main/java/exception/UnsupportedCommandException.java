package exception;

public class UnsupportedCommandException extends Exception {

    public UnsupportedCommandException(String message, Exception e) {
        super(message, e);
    }

    public UnsupportedCommandException(String message) {
        super(message);
    }
}
