package game_logic.event;

import java.util.EventListener;

public interface BoardListener extends EventListener {
    void boardChanged();
}
