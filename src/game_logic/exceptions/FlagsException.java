package game_logic.exceptions;

public class FlagsException extends GameException {
    public FlagsException() {
        super();
    }

    public FlagsException(String message) {
        super(message);
    }

    public FlagsException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlagsException(Throwable cause) {
        super(cause);
    }
}
