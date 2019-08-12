package game_logic.exceptions;

public class CannotPutFlagException extends FlagsException {
    public CannotPutFlagException() {
    }

    public CannotPutFlagException(String message) {
        super(message);
    }

    public CannotPutFlagException(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotPutFlagException(Throwable cause) {
        super(cause);
    }
}
