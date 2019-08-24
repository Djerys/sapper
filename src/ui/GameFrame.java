package ui;

import game_logic.Game;

import javax.swing.*;

public class GameFrame extends JFrame {
    public Game game;
    public GameFieldPanel panel;

    public GameFrame(Game game) {
        this.game = game;
        panel = new GameFieldPanel(game);
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel);
        setVisible(true);
    }
}
