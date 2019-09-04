package controller;

import game_logic.Game;
import game_logic.Difficulty;
import game_logic.Position;
import ui.GameFrame;
import ui.UI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameClient extends MouseAdapter implements GameController {
    private final Game game;
    private final UI ui;

    public static void main(String[] args) {
        var game = new Game(Difficulty.BEGINNER);
        UI ui = new GameFrame();
        GameController controller = new GameClient(game, ui);
        controller.start();
    }

    GameClient(Game game, UI ui) {
        this.game = game;
        this.ui = ui;
        this.ui.initialize(this);
    }

    @Override
    public Game getGame() {
        return game;
    }

    @Override
    public void start() {
        ui.getBoardPanel().addMouseListener(this);

        game.addBoardListener(ui.getBoardPanel()::repaint);
        game.addEndListener(ui.getBoardPanel()::repaint);
        game.addRestartListener(ui.getBoardPanel()::repaint);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int width = e.getX() / ui.getCellSize();
        int height = e.getY() / ui.getCellSize();
        var position = new Position(width, height);

        if (e.getButton() == MouseEvent.BUTTON1) {
            game.reveal(position);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            game.toggleFlag(position);
        }
    }
}
