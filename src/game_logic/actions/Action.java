package game_logic.actions;

import game_logic.Game;
import game_logic.GameField;

public abstract class Action {
    protected final Game game;

    public Action(Game game) {
        this.game = game;
    }

    public abstract void process();
}
