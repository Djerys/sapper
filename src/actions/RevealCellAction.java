package actions;

import actions.base.CellAction;
import game_logic.GameState;
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
        game.getField().setupMines();
        game.setState(GameState.GOING);
    }

    private void reveal() {
        game.getField().revealCell(vertical, horizontal);
        if (game.getField().isBlown()) {
            game.setState(GameState.LOSS);
//        } else if (game.getField().isClear()) {
            game.setState(GameState.WIN);
        }
    }
}
