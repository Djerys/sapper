package game_logic.event;

import java.util.EventListener;

public interface FieldListener extends EventListener {
    void fieldChanged();
}
