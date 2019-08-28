package game_logic;

import java.util.Random;

final class GameField {
    private int unusedFlagsCount;
    private boolean blown = false;
    private int openCleanCellsCount = 0;

    private final FieldSpace cells;
    private final int widthSize;
    private final int heightSize;
    private final int spaceSize;
    private final int minesCount;

    GameField(GameDifficulty difficulty) {
        this.widthSize = difficulty.getWidthSize();
        this.heightSize = difficulty.getHeightSize();
        this.spaceSize = heightSize * widthSize;
        this.minesCount = difficulty.getMinesCount();
        unusedFlagsCount = minesCount;
        cells = new FieldSpace(widthSize, heightSize);
    }

    boolean isBlown() {
        return blown;
    }

    boolean isClear() {
        var isAllClearCellsOpen = openCleanCellsCount == spaceSize - minesCount;
        return !blown && isAllClearCellsOpen;
    }

    boolean isRevealed(Position position) {
        return cells.get(position).isRevealed;
    }

    boolean hasMine(Position position) {
        return cells.get(position).hasMine;
    }

    boolean hasFlag(Position position) {
        return cells.get(position).hasFlag;
    }

    int nearMinesCount(Position position) {
        return cells.get(position).nearMinesCount;
    }

    int getUnusedFlagsCount() {
        return unusedFlagsCount;
    }

    void revealAllCells() {
        for (int i = 0; i < widthSize; i++) {
            for (int j = 0; j < heightSize; j++) {
                cells.get(new Position(i, j)).isRevealed = true;
            }
        }
        openCleanCellsCount = spaceSize - minesCount;
    }

    void revealNotFlaggedMines() {
        for (int i = 0; i < widthSize; i++) {
            for (int j = 0; j < heightSize; j++) {
                var cell = cells.get(new Position(i, j));
                if (!cell.hasFlag && cell.hasMine) {
                    cell.isRevealed = true;
                }
            }
        }
    }

    void reveal(Position position) {
        var cell = cells.get(position);
        if (blown) {
            return;
        }
        if (openCleanCellsCount == 0) {
            setupMines(position);
        }
        if (cell.hasMine) {
            blown = true;
        }
        innerReveal(position.getWidth(), position.getHeight());
    }

    boolean putFlag(Position position) {
        var cell = cells.get(position);
        if (cell.isRevealed || cell.hasFlag || unusedFlagsCount == 0) {
            return false;
        }
        cell.hasFlag = true;
        unusedFlagsCount--;
        return true;
    }

    boolean removeFlag(Position position) {
        var cell = cells.get(position);
        if (cell.isRevealed || !cell.hasFlag) {
            return false;
        }
        cell.hasFlag = false;
        unusedFlagsCount++;
        return true;
    }

    private void innerReveal(int width, int height) {
        if (isOutOfBounds(width, height)) return;
        var cell = cells.get(new Position(width, height));
        if (cell.isRevealed || cell.hasFlag) return;
        cell.isRevealed = true;
        openCleanCellsCount++;
        if (cell.nearMinesCount != 0) return;
        for (int wOffset = -1; wOffset <= 1; wOffset++) {
            for (int hOffset = -1; hOffset <= 1; hOffset++) {
                innerReveal(width + wOffset, height + hOffset);
            }
        }
    }

    private void setupMines(Position nonMinePosition) {
        randomMines(nonMinePosition);
        calculateNearMines();
    }

    private void randomMines(Position nonMinePosition) {
        var random = new Random();
        int i = 0;
        while (i < minesCount) {
            int width = random.nextInt(widthSize);
            int height = random.nextInt(heightSize);
            var position = new Position(width, height);
            var cell = cells.get(position);
            if (!cell.hasMine && !nonMinePosition.isNeighbourOf(position)) {
                cell.hasMine = true;
                i++;
            }
        }
    }

    private void calculateNearMines() {
        for (int i = 0; i < widthSize; i++) {
            for (int j = 0; j < heightSize; j++) {
                calculateNearMinesOfCell(i, j);
            }
        }
    }

    private void calculateNearMinesOfCell(int width, int height) {
        for (int wOffset = -1; wOffset <= 1; wOffset++) {
            for (int hOffset = -1; hOffset <= 1; hOffset++) {
                if (!isOutOfBounds(width + wOffset, height + hOffset)) {
                    var nearCell = cells.get(new Position(width + wOffset, height + hOffset));
                    cells.get(new Position(width, height)).nearMinesCount += nearCell.hasMine ? 1 : 0;
                }
            }
        }
    }

    private boolean isOutOfBounds(int width, int height) {
        return width < 0 || height < 0
                || width >= widthSize || height >= heightSize;
    }
}
