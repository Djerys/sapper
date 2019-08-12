package game_logic;

public class Game implements Runnable {
    private final GameField field;
    private final GameDifficulty difficulty;

    public Game(GameDifficulty difficulty) {
        this.difficulty = difficulty;
        this.field = new GameField(difficulty);
//        this.player = new Player();
    }

    @Override
    public void run() {

    }
}
