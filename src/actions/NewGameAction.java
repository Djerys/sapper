package actions;

import actions.base.Action;
import game_logic.GameDifficulty;
import game_logic.GameState;
import ui.GameClient;

public class NewGameAction extends Action {
    private final GameDifficulty difficulty;

    public NewGameAction(GameClient client, GameDifficulty difficulty) {
        super(client);
        this.difficulty = difficulty;
    }

    @Override
    public void process() {
        game.changeDifficulty(difficulty);
        game.getField().prepareToStart();
        game.setState(GameState.READY);
    }
}
