package game_logic.actions;

import game_logic.Game;
import game_logic.exceptions.AlreadyBlownException;
import game_logic.exceptions.AlreadyOpenCellException;

public class OpenCellAction extends CellAction {
    public OpenCellAction(Game game, int vertical, int horizontal) {
        super(game, vertical, horizontal);
    }

    @Override
    public void process() {
        try {
            game.field.openCell(vertical, horizontal);
        } catch (AlreadyBlownException e) {
            // TODO: handler
        } catch (AlreadyOpenCellException e) {
            // TODO: handler
        }
    }
}
