package game_logic.actions;

import game_logic.Game;

public abstract class CellAction extends Action {
    protected final int vertical;
    protected final int horizontal;

    public CellAction(Game game, int vertical, int horizontal) {
        super(game);
        this.vertical = vertical;
        this.horizontal = horizontal;
    }
}
