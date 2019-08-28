package game_logic.event;

import game_logic.Position;

public class ToggleFlagEvent {
    private final Position position;

    public ToggleFlagEvent(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
