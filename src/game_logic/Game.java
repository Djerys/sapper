package game_logic;

// Maybe this timer
import javax.swing.Timer;

public final class Game {
    public GameField field;

    private GameDifficulty difficulty;
    private GameState state = GameState.READY;

    public Game() {
        difficulty = GameDifficulty.INTERMEDIATE;
        field = new GameField(difficulty);
    }

    public Game(GameDifficulty difficulty) {
        this.difficulty = difficulty;
        field = new GameField(difficulty);
    }

    public void startNew() {
        field.initialize();
        state = GameState.GOING;
    }

    public void changeDifficulty(GameDifficulty difficulty) {
        this.difficulty = difficulty;
        field = new GameField(difficulty);
        state = GameState.READY;
    }
}
