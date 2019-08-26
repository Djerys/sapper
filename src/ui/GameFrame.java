package ui;

import game_logic.Game;

import javax.swing.*;

public class GameFrame extends JFrame {
    private final Game game;

    public GameFrame(Game game) {
        this.game = game;
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(new FieldPanel(game));
        setVisible(true);
    }

    public static void main(String[] args) {
        var frame = new GameFrame(new Game());
    }
}
