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

    public void revealCellOnField(int vertical, int horizontal) {
        state = GameState.GOING;
        field.revealCell(vertical, horizontal);
        if (field.isBlown()) {
            state = GameState.LOSS;
        } else if (field.isClear()) {
            state = GameState.WIN;
        }
    }

    public void putFlagOnField(int vertical, int horizontal) {
        field.putFlag(vertical, horizontal);
    }

    public void removeFlagFromField(int vertical, int horizontal) {
        field.removeFlag(vertical, horizontal);
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
