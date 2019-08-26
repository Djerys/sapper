package game_logic.event;

import java.util.EventListener;

public interface FewCellsListener extends EventListener {
    void fewCellsChanged(FewCellsEvent event);
}
