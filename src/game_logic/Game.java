package game_logic;

// Maybe this timer
import javax.swing.Timer;

public final class Game {
    public GameField field = new GameField(GameDifficulty.INTERMEDIATE);

    private Timer timer;
    private GameDifficulty difficulty;

    private final GameState state = GameState.READY;

    public Game() {
        // code...
    }

    public void initialize() {
        // code...
    }
}
