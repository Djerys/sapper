package game_logic;

import java.util.Random;
import java.util.Set;

final class GameField {
    private int unusedFlagsCount;
    private boolean blown = false;
    private int openCleanCellsCount = 0;

    private final Cell[][] space;
    private final int verticalSize;
    private final int horizontalSize;
    private final int spaceSize;
    private final int minesCount;

    GameField(GameDifficulty difficulty) {
        this.verticalSize = difficulty.getVerticalSize();
        this.horizontalSize = difficulty.getHorizontalSize();
        this.spaceSize = verticalSize * horizontalSize;
        this.minesCount = difficulty.getMinesCount();
        unusedFlagsCount = minesCount;
        space = new Cell[verticalSize][horizontalSize];
        initializeSpace();
    }

    boolean isBlown() {
        return blown;
    }

    boolean isClear() {
        var isAllClearCellsOpen = openCleanCellsCount == spaceSize - minesCount;
        return !blown && isAllClearCellsOpen;
    }

    boolean isRevealed(int vertical, int horizontal) {
        return space[vertical][horizontal].isRevealed;
    }

    boolean hasMine(int vertical, int horizontal) {
        return space[vertical][horizontal].hasMine;
    }

    boolean hasFlag(int vertical, int horizontal) {
        return space[vertical][horizontal].hasFlag;
    }

    int nearMinesCount(int vertical, int horizontal) {
        return space[vertical][horizontal].nearMinesCount;
    }

    int getUnusedFlagsCount() {
        return unusedFlagsCount;
    }

    void revealAllCells() {
        for (int i = 0; i < verticalSize; i++) {
            for (int j = 0; j < horizontalSize; j++) {
                space[i][j].isRevealed = true;
            }
        }
        openCleanCellsCount = spaceSize - minesCount;
    }

    void revealNotFlaggedMines() {
        for (int i = 0; i < verticalSize; i++) {
            for (int j = 0; j < horizontalSize; j++) {
                if (!space[i][j].hasFlag && space[i][j].hasMine) {
                    space[i][j].isRevealed = true;
                }
            }
        }
    }

    void reveal(int vertical, int horizontal) {
        var cell = space[vertical][horizontal];
        if (blown) {
            return;
        }
        if (openCleanCellsCount == 0) {
            setupMines(vertical, horizontal);
        }
        if (cell.hasMine) {
            blown = true;
        }
        innerReveal(vertical, horizontal);
    }

    boolean putFlag(int vertical, int horizontal) {
        var cell = space[vertical][horizontal];
        if (cell.isRevealed || cell.hasFlag || unusedFlagsCount == 0) {
            return false;
        }
        cell.hasFlag = true;
        unusedFlagsCount--;
        return true;
    }

    boolean removeFlag(int vertical, int horizontal) {
        var cell = space[vertical][horizontal];
        if (cell.isRevealed || !cell.hasFlag) {
            return false;
        }
        cell.hasFlag = false;
        unusedFlagsCount++;
        return true;
    }

    private void initializeSpace() {
        for (int i = 0; i < verticalSize; i++) {
            for (int j = 0; j < horizontalSize; j++) {
                space[i][j] = new Cell();
            }
        }
    }

    private void innerReveal(int vertical, int horizontal) {
        if (isOutOfBounds(vertical, horizontal)) return;
        var cell = space[vertical][horizontal];
        if (cell.isRevealed || cell.hasFlag) return;
        cell.isRevealed = true;
        openCleanCellsCount++;
        if (cell.nearMinesCount != 0) return;
        innerReveal(vertical - 1, horizontal - 1);
        innerReveal(vertical - 1, horizontal);
        innerReveal(vertical - 1, horizontal + 1);
        innerReveal(vertical, horizontal - 1);
        innerReveal(vertical, horizontal + 1);
        innerReveal(vertical + 1, horizontal - 1);
        innerReveal(vertical + 1, horizontal);
        innerReveal(vertical + 1, horizontal + 1);
    }

    private void setupMines(int nonMineVertical, int nonMineHorizontal) {
        randomMines(nonMineVertical, nonMineHorizontal);
        calculateNearMines();
    }

    private void randomMines(int nonMineVertical, int nonMineHorizontal) {
        var random = new Random();
        int i = 0;
        while (i < minesCount) {
            int vertical = random.nextInt(verticalSize);
            int horizontal = random.nextInt(horizontalSize);
            var shouldHaveMine = !dimensionRange(nonMineVertical).contains(vertical)
                    || !dimensionRange(nonMineHorizontal).contains(horizontal);
            if (!space[vertical][horizontal].hasMine && shouldHaveMine) {
                space[vertical][horizontal].hasMine = true;
                i++;
            }
        }
    }

    private Set<Integer> dimensionRange(int dimension) {
        return Set.of(dimension - 1, dimension, dimension + 1);
    }

    private void calculateNearMines() {
        for (int i = 0; i < verticalSize; i++) {
            for (int j = 0; j < horizontalSize; j++) {
                calculateNearMinesOfCell(i, j);
            }
        }
    }

    private void calculateNearMinesOfCell(int vertical, int horizontal) {
        for (int vOffset = -1; vOffset <= 1; vOffset++) {
            for (int hOffset = -1; hOffset <= 1; hOffset++) {
                if (!isOutOfBounds(vertical + vOffset, horizontal + hOffset)) {
                    var nearCell = space[vertical + vOffset][horizontal + hOffset];
                    space[vertical][horizontal].nearMinesCount += nearCell.hasMine ? 1 : 0;
                }
            }
        }
    }

    private boolean isOutOfBounds(int vertical, int horizontal) {
        return vertical < 0 || horizontal < 0
                || vertical >= verticalSize || horizontal >= horizontalSize;
    }
}
