package ui;

import game_logic.Game;
import game_logic.GameState;

import javax.swing.*;
import java.awt.*;

public class GameStatePanel extends JPanel {
    private final JLabel label = new JLabel();
    private final Game game;

    GameStatePanel(Game game) {
        this.game = game;
        add(label);
        setBorder(BorderFactory.createLoweredBevelBorder());
        setLayout(new FlowLayout());
        update();
    }

    public void update() {
        label.setText(game.getState().toString());
    }
}
