package ui;

import game_logic.Game;

public class GameClient {
    public Game game;
    public GameFrame frame;

    public GameClient(Game game) {
        this.game = game;
        this.frame = new GameFrame(game);
    }

    public static void main(String[] args) {
        var client = new GameClient(new Game());    }
}
