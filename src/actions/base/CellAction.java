package actions.base;

import actions.base.Action;
import ui.GameClient;

public abstract class CellAction extends Action {
    protected final int vertical;
    protected final int horizontal;

    public CellAction(GameClient client, int vertical, int horizontal) {
        super(client);
        this.vertical = vertical;
        this.horizontal = horizontal;
    }
}
