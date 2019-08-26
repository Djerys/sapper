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

    public int getVerticalSize() {
        return difficulty.getVerticalSize();
    }

    public int getHorizontalSize() {
        return difficulty.getHorizontalSize();
    }

    public int getUnusedFlagsCount() {
        return field.getUnusedFlagsCount();
    }

    public boolean isRevealed(int vertical, int horizontal) {
        return field.isRevealed(vertical, horizontal);
    }

    public boolean hasMine(int vertical, int horizontal) {
        return field.hasMine(vertical, horizontal);
    }

    public boolean hasFlag(int vertical, int horizontal) {
        return field.hasFlag(vertical, horizontal);
    }

    public int nearMinesCount(int vertical, int horizontal) {
        return field.nearMinesCount(vertical, horizontal);
    }

    public void reveal(int vertical, int horizontal) {
        state = GameState.GOING;
        field.reveal(vertical, horizontal);
        if (field.isBlown()) {
            state = GameState.LOSS;
        } else if (field.isClear()) {
            state = GameState.WIN;
        }
    }

    public void toggleFlag(int vertical, int horizontal) {
        field.putFlag(vertical, horizontal);
        field.removeFlag(vertical, horizontal);
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
