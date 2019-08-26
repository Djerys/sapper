package ui;

import game_logic.Game;
import game_logic.event.CellEvent;
import game_logic.event.CellListener;

import javax.swing.*;

public class GameFrame extends JFrame {
    private final Game game;
    private final FieldPanel panel;

    public GameFrame(Game game) {
        this.game = game;
        panel = new FieldPanel(game);
        game.addCellListener(e -> panel.updateCell(e.getVertical(), e.getHorizontal()));
        game.addFewCellsListener(e -> panel.updateAllCells());
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        var frame = new GameFrame(new Game());
    }
}
