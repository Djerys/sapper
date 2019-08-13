package game_logic.actions;

import game_logic.Game;
import game_logic.exceptions.AlreadyOpenCellException;
import game_logic.exceptions.NoFlagOnCellException;

public class RemoveFlagAction extends CellAction {
    public RemoveFlagAction(Game game, int vertical, int horizontal) {
        super(game, vertical, horizontal);
    }

    @Override
    public void process() {
        try {
            game.field.removeFlag(vertical, horizontal);
        } catch (NoFlagOnCellException e) {
            // TODO: handler
        } catch (AlreadyOpenCellException e) {
            // TODO: handler
        }
    }
}
