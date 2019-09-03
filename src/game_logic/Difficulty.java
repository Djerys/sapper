package game_logic;

public enum Difficulty {
    BEGINNER(9, 9, 10), 
    INTERMEDIATE(16, 16, 40),
    PROFESSIONAL(30, 16, 99);

    private final int heightSize;
    private final int widthSize;
    private final int minesCount;

    Difficulty(int widthSize, int heightSize, int minesCount) {
        this.widthSize = widthSize;
        this.heightSize = heightSize;
        this.minesCount = minesCount;
    }

    public int getWidthSize() {
        return widthSize;
    }

    public int getHeightSize() {
        return heightSize;
    }

    public int getMinesCount() {
        return minesCount;
    }

    @Override
    public String toString() {
        return "GameDifficulty{" +
                "verticalSize=" + heightSize +
                ", horizontalSize=" + widthSize +
                ", minesNumber=" + minesCount +
                '}';
    }
}
