package ui;

import game_logic.Game;

public class GameFrame {
    Game game;
    GameFieldPanel panel;

    public GameFrame(Game game) {
        this.game = game;
        panel = new GameFieldPanel(game);
    }
}
