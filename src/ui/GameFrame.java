package ui;

import game_logic.Game;
import game_logic.GameDifficulty;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
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

    public static void main(String[] args) {
        var frame = new GameFrame(new Game(GameDifficulty.INTERMEDIATE));
    }
}
