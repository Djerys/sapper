package game_logic.actions;

import game_logic.Game;
import game_logic.exceptions.AlreadyOpenCellException;
import game_logic.exceptions.NoFreeFlagsException;

public class PutFlagAction extends CellAction {
    public PutFlagAction(Game game, int vertical, int horizontal) {
        super(game, vertical, horizontal);
    }

    @Override
    public void process() {
        try {
            game.field.putFlag(vertical, horizontal);
        } catch (NoFreeFlagsException e) {
            // TODO: handler
        } catch (AlreadyOpenCellException e) {
            // TODO: handler
        }
    }
}
