package game_logic.exceptions;

public class FlagException extends GameException {
    public FlagException() {
        super();
    }

    public FlagException(String message) {
        super(message);
    }

    public FlagException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlagException(Throwable cause) {
        super(cause);
    }
}
