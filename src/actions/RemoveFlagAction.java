package actions;

import actions.base.CellAction;
import ui.GameClient;

public class RemoveFlagAction extends CellAction {
    public RemoveFlagAction(GameClient client, int vertical, int horizontal) {
        super(client, vertical, horizontal);
    }

    @Override
    public void process() {
        client.game.field.removeFlag(vertical, horizontal);
    }
}
