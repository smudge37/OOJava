package battleships;

public class CoordsInvalidException extends RuntimeException {
    public CoordsInvalidException() {

    }

    public CoordsInvalidException(String message) {
        super (message);
    }

    public CoordsInvalidException(Throwable cause) {
        super (cause);
    }

    public CoordsInvalidException(String message, Throwable cause) {
            super (message, cause);
        }
}
