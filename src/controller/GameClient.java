package controller;

import game_logic.Game;
import game_logic.Difficulty;
import game_logic.Position;
import ui.gui.GameFrame;
import ui.UI;
import ui.gui.UIConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class GameClient extends MouseAdapter implements GameController {
    private final Game game;
    private final UI ui;

    public static void main(String[] args) {
        var game = new Game(Difficulty.BEGINNER);
        UI ui = new GameFrame();
        GameController controller = new GameClient(game, ui);
    }

    GameClient(Game game, UI ui) {
        this.game = game;
        this.ui = ui;
        this.ui.setController(this);
        // TODO: addListeners()
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int width = e.getX() / UIConstants.CELL_SIZE;
        int height = e.getY() / UIConstants.CELL_SIZE;
        var position = new Position(width, height);

        if (e.getButton() == MouseEvent.BUTTON1) {
            game.reveal(position);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            game.toggleFlag(position);
        }
    }

    @Override
    public Game getGame() {
        return game;
    }
}
