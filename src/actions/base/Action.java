package actions.base;

import ui.GameClient;

public abstract class Action {
    protected final GameClient client;

    public Action(GameClient client) {
        this.client = client;
    }

    public abstract void process();
}
