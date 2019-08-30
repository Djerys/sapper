package game_logic;

import java.util.Arrays;

final class FieldSpace {
    private final Cell[][] space;

    FieldSpace(int widthSize, int heightSize) {
        space = new Cell[heightSize][widthSize];
        for (int i = 0; i < heightSize; i++) {
            for (int j = 0; j < widthSize; j++) {
                space[i][j] = new Cell();
            }
        }
    }

    Cell get(Position position) {
        return space[position.getHeight()][position.getWidth()];
    }

    @Override
    public String toString() {
        return Arrays.toString(space);
    }
}