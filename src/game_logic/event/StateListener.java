package game_logic.event;

import java.util.EventListener;

public interface StateListener extends EventListener {
    void stateChanged(StateEvent event);
}
