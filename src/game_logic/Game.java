package game_logic;


public final class Game {
    private GameState state;
    private GameField field;
    private GameDifficulty difficulty;

    public Game() {
        restart(GameDifficulty.INTERMEDIATE);
    }

    public Game(GameDifficulty difficulty) {
        restart(difficulty);
    }

    public GameState getState() {
        return state;
    }

    private GameDifficulty getDifficulty() {
        return difficulty;
    }

    public void tryRevealCell(int vertical, int horizontal) {
        state = GameState.GOING;
        field.tryRevealCell(vertical, horizontal);
        if (field.isBlown()) {
            state = GameState.LOSS;
        } else if (field.isClear()) {
            state = GameState.WIN;
        }
    }

    public void tryPutFlag(int vertical, int horizontal) {
        field.tryPutFlag(vertical, horizontal);
    }

    public void tryRemoveFlag(int vertical, int horizontal) {
        field.tryRemoveFlag(vertical, horizontal);
    }

    public int getVerticalSize() {
        return difficulty.getVerticalSize();
    }

    public int getHorizontalSize() {
        return difficulty.getHorizontalSize();
    }

    public int getUnusedFlagsCount() {
        return field.getUnusedFlagsCount();
    }

    public void restart(GameDifficulty difficulty) {
        this.difficulty = difficulty;
        field = new GameField(difficulty);
        state = GameState.READY;
    }

    public void finish() {
        switch (state) {
            case WIN:
                field.revealAllCells();
                break;
            case LOSS:
                field.revealNotFlaggedMines();
        }
    }

    public void printField() {
        field.print();
    }
}
