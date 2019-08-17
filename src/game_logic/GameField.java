package game_logic;

import java.util.Objects;
import java.util.Random;

public final class GameField {
    private boolean blown = false;
    private int usedFlagsCount = 0;
    private int openCleanCellsCount = 0;
    private Cell[][] space;

    private final int verticalSize;
    private final int horizontalSize;
    private final int spaceSize;
    private final int minesNumber;

    private static class Cell {
        boolean isOpen = false;
        boolean hasMine = false;
        boolean hasFlag = false;
        int nearMinesCount = 0;
    }

    public GameField(GameDifficulty difficulty) {
        this.verticalSize = difficulty.getVerticalSize();
        this.horizontalSize = difficulty.getHorizontalSize();
        this.spaceSize = verticalSize * horizontalSize;
        this.minesNumber = difficulty.getMinesNumber();
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

    // TODO: randomize mine places
    public void setupMines() {
        randomMines();
        calculateNearMinesCount();
    }

    public void prepareToStart() {
        for (int i = 0; i < verticalSize; i++) {
            for (int j = 0; j < horizontalSize; j++) {
                space[i][j] = new Cell();
            }
        }
    }

    /**
     * Reveals all cells in field space.
     */
    public void revealAllCells() {
        for (int i = 0; i < verticalSize; i++) {
            for (int j = 0; j < horizontalSize; j++) {
                space[i][j].isOpen = true;
            }
        }
        openCleanCellsCount = spaceSize - minesNumber;
    }

    /**
     * Reveals cell located on given coordinates.
     *
     * @param vertical   vertical coordinate for field space
     * @param horizontal horizontal coordinate for field space
     * @return true if cell was open else false
     */
    public boolean revealCell(int vertical, int horizontal) {
        checkSpaceIndexes(vertical, horizontal);
        var cell = space[vertical][horizontal];
        if (cell.isOpen || cell.hasFlag) {
            return false;
        }
        cell.isOpen = true;
        if (cell.hasMine) {
            blown = true;
        } else {
            openCleanCellsCount++;
        }
        return true;
    }

    /**
     * Puts flag on cell located on given coordinates.
     *
     * @param vertical   vertical coordinate for field space
     * @param horizontal horizontal coordinate for field space
     * @return true if cell was marked else false
     */
    public boolean putFlag(int vertical, int horizontal) {
        checkSpaceIndexes(vertical, horizontal);
        var cell = space[vertical][horizontal];
        if (cell.isOpen || usedFlagsCount == minesNumber) {
            return false;
        }
        cell.hasFlag = true;
        usedFlagsCount++;
        return true;
    }

    /**
     * Removes flag from cell located on given coordinates.
     *
     * @param vertical   vertical coordinate for field space
     * @param horizontal horizontal coordinate for field space
     * @return true if flag was removed else false
     */
    public boolean removeFlag(int vertical, int horizontal) {
        checkSpaceIndexes(vertical, horizontal);
        var cell = space[vertical][horizontal];
        if (cell.isOpen || !cell.hasFlag) {
            return false;
        }
        cell.hasFlag = false;
        usedFlagsCount--;
        return true;
    }

    private void randomMines() {
        var random = new Random();
        int i = 0;
        while (i < minesNumber) {
            int vertical = random.nextInt(verticalSize);
            int horizontal = random.nextInt(horizontalSize);
            if (!space[vertical][horizontal].hasMine) {
                space[vertical][horizontal].hasMine = true;
                i++;
            }
        }
    }

    private void calculateNearMinesCount() {
        for (int i = 0; i < verticalSize; i++) {
            for (int j = 0; j < horizontalSize; j++) {
                calculateNears(i, j);
            }
        }
    }
    
    private void calculateNears(int vertical, int horizontal) {
        for (int verticalOffset = -1; verticalOffset < 1; verticalOffset++) {
            for (int horizontalOffset = -1; horizontalOffset < 1; horizontalOffset++) {
                var nearCell = space[vertical + verticalOffset][horizontal + horizontalOffset];
                if (!isOutOfBounds(vertical, horizontal)) {
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
    }

    private void checkSpaceIndexes(int vertical, int horizontal) {
        Objects.checkIndex(vertical, space.length);
        Objects.checkIndex(horizontal, space[vertical].length);
    }
}
