package ui;

import game_logic.Game;
import game_logic.GameDifficulty;

import javax.swing.*;

public class GameFrame extends JFrame {
    private final Game game;
    private final FieldPanel panel;

    public GameFrame(Game game) {
        this.game = game;
        panel = new FieldPanel(game);
        game.addToggleFlagListener(e -> panel.updateCell(e.getVertical(), e.getHorizontal()));
        game.addRevealListener(e -> panel.updateAllCells());
        game.addEndListener(e -> panel.updateAllCells());
        setSize(322, 346);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        var frame = new GameFrame(new Game(GameDifficulty.BEGINNER));
    }
}
