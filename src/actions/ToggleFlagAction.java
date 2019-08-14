package actions;

import actions.base.CellAction;
import ui.GameClient;

public class ToggleFlagAction extends CellAction {
    public ToggleFlagAction(GameClient client, int vertical, int horizontal) {
        super(client, vertical, horizontal);
    }

    @Override
    public void process() {
    }
}
