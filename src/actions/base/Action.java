package actions.base;

import game_logic.Game;
import ui.GameClient;

public abstract class Action {
    protected final Game game;

    public Action(GameClient client) {
        this.game = client.game;
    }

    public abstract void process();
}
