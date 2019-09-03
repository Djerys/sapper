package ui.gui;

import game_logic.Game;
import game_logic.Difficulty;
import ui.UI;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame implements UI {
    private final Game game;
    private final FieldPanel fieldPanel;
    private final GameStatePanel statePanel;

    public GameFrame(Game game) {
        this.game = game;
        fieldPanel = new FieldPanel(game);
        statePanel = new GameStatePanel(game);
        add(fieldPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setTitle("MineSweeper");
        setVisible(true);
        setLocation(new Point(400, 350));
    }

    @Override
    public void update() {
        fieldPanel.repaint();
    }

    public static void main(String[] args) {
        var frame = new GameFrame(new Game(Difficulty.PROFESSIONAL));
    }
}
