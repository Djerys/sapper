package game_logic.event;

public class ToggleFlagEvent {
    private final int vertical;
    private final int horizontal;

    public ToggleFlagEvent(int vertical, int horizontal) {
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
