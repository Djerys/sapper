package game_logic;

// Maybe this timer

public final class Game {
    private GameState state = GameState.READY;
    private GameField field;
    private GameDifficulty difficulty;

    public Game() {
        difficulty = GameDifficulty.INTERMEDIATE;
        field = new GameField(difficulty);
    }

    public Game(GameDifficulty difficulty) {
        this.difficulty = difficulty;
        field = new GameField(difficulty);
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public GameField getField() {
        return field;
    }

    public void changeDifficulty(GameDifficulty difficulty) {
        this.difficulty = difficulty;
        field = new GameField(difficulty);
        state = GameState.READY;
    }

    public void start() {
        field.prepareToStart();
        state = GameState.READY;
    }
}
