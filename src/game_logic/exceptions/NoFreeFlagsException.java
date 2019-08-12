package game_logic.exceptions;

public class NoFreeFlagsException extends FlagsException {
    public NoFreeFlagsException() {
        super();
    }

    public NoFreeFlagsException(String message) {
        super(message);
    }

    public NoFreeFlagsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoFreeFlagsException(Throwable cause) {
        super(cause);
    }
}
