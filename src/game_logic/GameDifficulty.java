package game_logic;

public enum GameDifficulty {
    BEGINNER(9, 9, 10), 
    INTERMEDIATE(16, 16, 40),
    PROFESSIONAL(16, 30, 99);

    private final int verticalSize;
    private final int horizontalSize;
    private final int minesCount;

    GameDifficulty(int verticalSize, int horizontalSize, int minesCount) {
        this.verticalSize = verticalSize;
        this.horizontalSize = horizontalSize;
        this.minesCount = minesCount;
    }

    public int getVerticalSize() {
        return verticalSize;
    }

    public int getHorizontalSize() {
        return horizontalSize;
    }

    public int getMinesCount() {
        return minesCount;
    }

    @Override
    public String toString() {
        return "GameDifficulty{" +
                "verticalSize=" + verticalSize +
                ", horizontalSize=" + horizontalSize +
                ", minesNumber=" + minesCount +
                '}';
    }
}
