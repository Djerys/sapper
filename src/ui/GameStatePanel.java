package ui;

import game_logic.Game;

import javax.swing.*;
import java.awt.*;

class GameStatePanel extends JPanel {
    private final Game game;
    private final JLabel label;

    GameStatePanel(Game game) {
        this.game = game;
        label = new JLabel(game.getState().toString());
        label.setFont(new Font("Consolas", Font.BOLD, 18));
        add(label);
    }

    void update() {
        label.setText(game.getState().toString());
    }
}
