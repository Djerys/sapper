package game_logic.exceptions;

public class NoFlagOnCellException extends FlagException {
    public NoFlagOnCellException() {
        super();
    }

    public NoFlagOnCellException(String message) {
        super(message);
    }

    public NoFlagOnCellException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoFlagOnCellException(Throwable cause) {
        super(cause);
    }
}
