package virtualLibrary.LibraryExceptions;

public class DoubleReservationException extends RuntimeException {
        public DoubleReservationException() {

        }

        public DoubleReservationException(String message) {
            super (message);
        }

        public DoubleReservationException(Throwable cause) {
            super (cause);
        }

        public DoubleReservationException(String message, Throwable cause) {
            super (message, cause);
        }
}
