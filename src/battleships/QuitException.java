package battleships;

public class QuitException extends RuntimeException {
    public QuitException() {

    }

    public QuitException(String message) {
        super (message);
    }

    public QuitException(Throwable cause) {
        super (cause);
    }

    public QuitException(String message, Throwable cause) {
            super (message, cause);
        }
}
