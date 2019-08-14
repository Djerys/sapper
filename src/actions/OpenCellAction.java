package actions;

import actions.base.CellAction;
import ui.GameClient;

public class OpenCellAction extends CellAction {
    public OpenCellAction(GameClient client, int vertical, int horizontal) {
        super(client, vertical, horizontal);
    }

    @Override
    public void process() {
        client.game.field.openCell(vertical, horizontal);
    }
}
