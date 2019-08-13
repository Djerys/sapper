package game_logic.exceptions;

public class AlreadyOpenCellException extends GameException {
    public AlreadyOpenCellException() {
        super();
    }

    public AlreadyOpenCellException(String message) {
        super(message);
    }

    public AlreadyOpenCellException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyOpenCellException(Throwable cause) {
        super(cause);
    }
}
