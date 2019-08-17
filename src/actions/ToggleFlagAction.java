package actions;

import actions.base.CellAction;
import ui.GameClient;

public class ToggleFlagAction extends CellAction {
    public ToggleFlagAction(GameClient client, int vertical, int horizontal) {
        super(client, vertical, horizontal);
    }

    @Override
    public void process() {
        if (game.getField().putFlag(vertical, horizontal)) {
            // UI handler gor putting...
        } else if (game.getField().removeFlag(vertical, horizontal)) {
            // UI handler for removing
        }
    }
}
