package game_logic.event;

import java.util.EventListener;

public interface RevealListener extends EventListener {
    void revealed(RevealEvent event);
}
