package game_logic;


import game_logic.exceptions.AlreadyBlownException;
import game_logic.exceptions.CannotPutFlagException;
import game_logic.exceptions.NoFlagOnCellException;
import game_logic.exceptions.NoFreeFlagsException;

import java.util.Objects;


public final class GameField {
    private final int verticalSize;
    private final int horizontalSize;
    private final int minesNumber;

    private boolean blown;
    private int usedFlagsNumber = 0;
    private Cell[][] space;

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
     * Checks if mine was opened.
     *
     * @return true if mine was opened, else false
     */
    public boolean isBlown() {
        return blown;
    }

    /**
     * Opens cell located on given coordinates.
     *
     * @param vertical vertical coordinate for field space
     * @param horizontal horizontal coordinate for field space
     * @throws AlreadyBlownException if mine already detected
     */
    public void openCell(int vertical, int horizontal) throws AlreadyBlownException {
        if (blown) {
            throw new AlreadyBlownException("Mine ");
        }
        checkSpaceIndexes(vertical, horizontal);
        space[vertical][horizontal].isOpen = true;
        if (space[vertical][horizontal].hasMine) {
            blown = true;
        }
    }

    /**
     * Puts flag on cell located on given coordinates.
     *
     * @param vertical vertical coordinate for field space
     * @param horizontal horizontal coordinate for field space
     * @throws NoFreeFlagsException if all flags already used
     * @throws CannotPutFlagException if cell already opened
     */
    public void putFlag(int vertical, int horizontal) throws NoFreeFlagsException, CannotPutFlagException {
        if (usedFlagsNumber == minesNumber) {
            throw new NoFreeFlagsException();
        }
        checkSpaceIndexes(vertical, horizontal);
        if (space[vertical][horizontal].isOpen) {
            throw new CannotPutFlagException();
        }
        space[vertical][horizontal].hasFlag = true;
        usedFlagsNumber++;
    }

    /**
     * Removes flag from cell located on given coordinates.
     *
     * @param vertical vertical coordinate for field space
     * @param horizontal horizontal coordinate for field space
     * @throws NoFlagOnCellException if cell doesn't have a flag
     */
    public void removeFlag(int vertical, int horizontal) throws NoFlagOnCellException {
        checkSpaceIndexes(vertical, horizontal);
        if (!space[vertical][horizontal].hasFlag) {
            throw new NoFlagOnCellException();
        }
        space[vertical][horizontal].hasFlag = false;
        usedFlagsNumber--;
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
