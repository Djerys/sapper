package game_logic.event;

import java.util.EventListener;

public interface CellListener extends EventListener {
    void cellChanged(CellEvent event);
}
