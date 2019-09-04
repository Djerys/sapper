package game_logic;

public final class Difficulty {
    public static final Difficulty EASY = new Difficulty(9, 9, 10);
    public static final Difficulty INTERMEDIATE = new Difficulty(16, 16, 40);
    public static final Difficulty HARD = new Difficulty(30, 16, 99);

    private final int heightSize;
    private final int widthSize;
    private final int minesCount;

    public Difficulty(int widthSize, int heightSize, int minesCount) {
        this.widthSize = widthSize;
        this.heightSize = heightSize;
        this.minesCount = minesCount;
    }

    int getWidthSize() {
        return widthSize;
    }

    int getHeightSize() {
        return heightSize;
    }

    int getMinesCount() {
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
