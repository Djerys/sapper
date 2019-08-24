package actions;

import actions.base.CellAction;
import ui.GameClient;

public class RevealCellAction extends CellAction {
    public RevealCellAction(GameClient client, int vertical, int horizontal) {
        super(client, vertical, horizontal);
    }

    @Override
    public void process() {
        switch (game.getState()) {
            case READY:
                setup();
            case GOING:
                reveal();
                break;
            case WIN:
            case LOSS:
                break;
        }
    }

    private void setup() {
        // code...
    }

    private void reveal() {
        // code...
    }
}
