package game_logic;

import java.util.Arrays;
import java.util.Objects;

public final class GameField {
    private boolean blown = false;
    private int usedFlagsNumber = 0;
    private Cell[][] space;

    private final int verticalSize;
    private final int horizontalSize;
    private final int minesNumber;

    private static class Cell {
        boolean isOpen = false;
        boolean hasMine = false;
        boolean hasFlag = false;
    }

    public GameField(GameDifficulty difficulty) {
        this.verticalSize = difficulty.getVerticalSize();
        this.horizontalSize = difficulty.getHorizontalSize();
        this.minesNumber = difficulty.getMinesNumber();
        initializeSpace();
    }

    public boolean isBlown() {
        return blown;
    }

    public int getUsedFlagsNumber() {
        return usedFlagsNumber;
    }

    // TODO: randomize mine places
    public void setupMines() {
        // code...
    }

    /**
     * Opens all cells in field space.
     */
    public void revealAllCells() {
        for (int i = 0; i < verticalSize; i++) {
            for (int j = 0; j < horizontalSize; j++) {
                space[i][j].isOpen = true;
            }
        }
    }

    /**
     * Opens cell located on given coordinates.
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
        if (cell.isOpen || usedFlagsNumber == minesNumber) {
            return false;
        }
        cell.hasFlag = true;
        usedFlagsNumber++;
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
        usedFlagsNumber--;
        return true;
    }

    private void checkSpaceIndexes(int vertical, int horizontal) {
        Objects.checkIndex(vertical, space.length);
        Objects.checkIndex(horizontal, space[vertical].length);
    }

    private void initializeSpace() {
        space = new Cell[verticalSize][horizontalSize];
        for (int i = 0; i < verticalSize; i++) {
            for (int j = 0; j < horizontalSize; j++) {
                space[i][j] = new Cell();
            }
        }
    }
}
