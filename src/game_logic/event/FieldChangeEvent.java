package game_logic.event;

public class FieldChangeEvent {
    private final ChangeType changeType;

    public FieldChangeEvent(ChangeType changeType) {
        this.changeType = changeType;
    }

    public ChangeType getChangeType() {
        return changeType;
    }
}