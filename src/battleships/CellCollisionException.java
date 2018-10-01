package battleships;

public class CellCollisionException extends RuntimeException {
        public CellCollisionException() {

        }

        public CellCollisionException(String message) {
            super (message);
        }

        public CellCollisionException(Throwable cause) {
            super (cause);
        }

        public CellCollisionException(String message, Throwable cause) {
            super (message, cause);
        }
}
