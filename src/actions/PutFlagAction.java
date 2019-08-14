package actions;

import actions.base.CellAction;
import ui.GameClient;

public class PutFlagAction extends CellAction {
    public PutFlagAction(GameClient client, int vertical, int horizontal) {
        super(client, vertical, horizontal);
    }

    @Override
    public void process() {
        client.game.field.putFlag(vertical, horizontal);
    }
}
