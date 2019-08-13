package game_logic;

import game_logic.exceptions.AlreadyBlownException;
import game_logic.exceptions.AlreadyOpenCellException;
import game_logic.exceptions.NoFlagOnCellException;
import game_logic.exceptions.NoFreeFlagsException;

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

    public int getUsedFlagsNumber() {
        return usedFlagsNumber;
    }

    // TODO: randomize mine places
    public void initialize() {
    }

    /**
     * Opens all cells in field space.
     */
    public void openAllCells() {
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
     * @throws AlreadyBlownException    if mine is already detected
     * @throws AlreadyOpenCellException if cell is already open
     */
    public void openCell(int vertical, int horizontal)
            throws AlreadyBlownException, AlreadyOpenCellException {
        if (blown) {
            throw new AlreadyBlownException();
        }
        checkSpaceIndexes(vertical, horizontal);
        requireNonOpenCell(vertical, horizontal).isOpen = true;
        space[vertical][horizontal].isOpen = true;
        if (space[vertical][horizontal].hasMine) {
            blown = true;
        }
    }

    /**
     * Puts flag on cell located on given coordinates.
     *
     * @param vertical   vertical coordinate for field space
     * @param horizontal horizontal coordinate for field space
     * @throws NoFreeFlagsException     if all flags already used
     * @throws AlreadyOpenCellException if cell is already open
     */
    public void putFlag(int vertical, int horizontal)
            throws NoFreeFlagsException, AlreadyOpenCellException {
        if (usedFlagsNumber == minesNumber) {
            throw new NoFreeFlagsException();
        }
        checkSpaceIndexes(vertical, horizontal);
        requireNonOpenCell(vertical, horizontal).hasFlag = true;
        usedFlagsNumber++;
    }

    /**
     * Removes flag from cell located on given coordinates.
     *
     * @param vertical   vertical coordinate for field space
     * @param horizontal horizontal coordinate for field space
     * @throws NoFlagOnCellException    if cell doesn't have a flag
     * @throws AlreadyOpenCellException if cell is already open
     */
    public void removeFlag(int vertical, int horizontal)
            throws NoFlagOnCellException, AlreadyOpenCellException {
        checkSpaceIndexes(vertical, horizontal);
        if (!space[vertical][horizontal].hasFlag) {
            throw new NoFlagOnCellException();
        }
        requireNonOpenCell(vertical, horizontal).hasFlag = false;
        usedFlagsNumber--;
    }

    private void checkSpaceIndexes(int vertical, int horizontal) {
        Objects.checkIndex(vertical, space.length);
        Objects.checkIndex(horizontal, space[vertical].length);
    }

    private Cell requireNonOpenCell(int vertical, int horizontal)
            throws AlreadyOpenCellException {
        if (!space[vertical][horizontal].isOpen) {
            throw new AlreadyOpenCellException();
        }
        return space[vertical][horizontal];
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
