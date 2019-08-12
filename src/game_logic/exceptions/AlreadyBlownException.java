package game_logic.exceptions;

public class AlreadyBlownException extends GameException {
    public AlreadyBlownException() {
    }

    public AlreadyBlownException(String message) {
        super(message);
    }

    public AlreadyBlownException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyBlownException(Throwable cause) {
        super(cause);
    }
}
