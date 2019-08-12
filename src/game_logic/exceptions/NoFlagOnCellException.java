package game_logic.exceptions;

public class NoFlagOnCellException extends FlagsException {
    public NoFlagOnCellException() {
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
