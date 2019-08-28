package game_logic.event;

import java.util.EventListener;

public interface ToggleFlagListener extends EventListener {
    void flagToggled(ToggleFlagEvent event);
}
