package game_logic.event;

public class CellEvent {
    private final int vertical;
    private final int horizontal;

    public CellEvent(int vertical, int horizontal) {
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

    public int getVertical() {
        return vertical;
    }

    public int getHorizontal() {
        return horizontal;
    }
}
