package ui;

import game_logic.Game;

import javax.swing.*;
import java.awt.*;

public class FlagsCountPanel extends JPanel {
    private final Game game;
    private JLabel label;

    FlagsCountPanel(Game game) {
        this.game = game;
        label = new JLabel(Integer.toString(game.getUnusedFlagsCount()));
        add(label);
    }

    public void update() {
        label.setText(Integer.toString(game.getUnusedFlagsCount()));
    }
}
