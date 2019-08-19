package game_logic;

import java.util.Random;
import java.util.Set;

public final class GameField {
    private boolean blown = false;
    private int usedFlagsCount = 0;
    private int openCleanCellsCount = 0;

    private final Cell[][] space;
    private final int verticalSize;
    private final int horizontalSize;
    private final int spaceSize;
    private final int minesNumber;

    GameField(GameDifficulty difficulty) {
        this.verticalSize = difficulty.getVerticalSize();
        this.horizontalSize = difficulty.getHorizontalSize();
        this.spaceSize = verticalSize * horizontalSize;
        this.minesNumber = difficulty.getMinesNumber();
        space = new Cell[verticalSize][horizontalSize];
        initializeSpace();
    }

    public boolean isBlown() {
        return blown;
    }

    public boolean isClear() {
        return !blown && openCleanCellsCount == spaceSize - minesNumber;
    }

    public int getUsedFlagsCount() {
        return usedFlagsCount;
    }

    /**
     * Reveals all cells in field space.
     */
    public void revealAllCells() {
        for (int i = 0; i < verticalSize; i++) {
            for (int j = 0; j < horizontalSize; j++) {
                space[i][j].isRevealed = true;
            }
        }
        openCleanCellsCount = spaceSize - minesNumber;
    }

    /**
     * Reveals cell located on given coordinates.
     *
     * @param vertical   vertical coordinate for field space
     * @param horizontal horizontal coordinate for field space
     */
    public void revealCell(int vertical, int horizontal) {
        var cell = space[vertical][horizontal];
        if (cell.isRevealed || cell.hasFlag) {
            return;
        }
        if (openCleanCellsCount == 0) {
            setupMines(vertical, horizontal);
        }
        if (cell.hasMine) {
            blown = true;
        } else {
            openCleanCellsCount++;
        }
        revealAround(vertical, horizontal);
    }

    /**
     * Puts flag on cell located on given coordinates.
     *
     * @param vertical   vertical coordinate for field space
     * @param horizontal horizontal coordinate for field space
     * @return true if cell was marked else false
     */
    public void putFlag(int vertical, int horizontal) {
        var cell = space[vertical][horizontal];
        if (cell.isRevealed || usedFlagsCount == minesNumber) {
            return;
        }
        cell.hasFlag = true;
        usedFlagsCount++;
    }

    /**
     * Removes flag from cell located on given coordinates.
     *
     * @param vertical   vertical coordinate for field space
     * @param horizontal horizontal coordinate for field space
     * @return true if flag was removed else false
     */
    public void removeFlag(int vertical, int horizontal) {
        var cell = space[vertical][horizontal];
        if (cell.isRevealed || !cell.hasFlag) {
            return;
        }
        cell.hasFlag = false;
        usedFlagsCount--;
    }

    public void print() {
        for (var row : space) {
            for (var cell : row) {
                if (cell.hasFlag) {
                    System.out.print("| ");
                } else if (!cell.isRevealed) {
                    System.out.print("# ");
                } else if (cell.hasMine) {
                    System.out.print("* ");
                } else if (cell.nearMinesCount != 0) {
                    System.out.print(cell.nearMinesCount + " ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }

    private void initializeSpace() {
        for (int i = 0; i < verticalSize; i++) {
            for (int j = 0; j < horizontalSize; j++) {
                space[i][j] = new Cell();
            }
        }
    }

    private void setupMines(int nonMineVertical, int nonMineHorizontal) {
        randomMines(nonMineVertical, nonMineHorizontal);
        calculateNearMinesCount();
    }

    private void randomMines(int nonMineVertical, int nonMineHorizontal) {
        var random = new Random();
        int i = 0;
        while (i < minesNumber) {
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

    private void calculateNearMinesCount() {
        for (int i = 0; i < verticalSize; i++) {
            for (int j = 0; j < horizontalSize; j++) {
                calculateNears(i, j);
            }
        }
    }

    private void calculateNears(int vertical, int horizontal) {
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

    private void revealAround(int vertical, int horizontal) {
        if (isOutOfBounds(vertical, horizontal)) return;
        var cell = space[vertical][horizontal];
        if (cell.isRevealed) return;
        if (cell.hasMine) return;
        cell.isRevealed = true;
        if (cell.nearMinesCount != 0) return;
        revealAround(vertical - 1, horizontal - 1);
        revealAround(vertical - 1, horizontal);
        revealAround(vertical - 1, horizontal + 1);
        revealAround(vertical, horizontal - 1);
        revealAround(vertical, horizontal + 1);
        revealAround(vertical + 1, horizontal - 1);
        revealAround(vertical + 1, horizontal);
        revealAround(vertical + 1, horizontal + 1);
    }
}
