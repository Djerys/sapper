package ui;

import game_logic.Game;

import javax.swing.*;
import java.awt.*;

class CellPanel extends JPanel {
    private final int vertical;
    private final int horizontal;

    private Game game;

    private JLabel label = new JLabel();

    CellPanel(Game game, int vertical, int horizontal) {
        this.game = game;
        this.vertical = vertical;
        this.horizontal = horizontal;
        add(label);
        setPreferredSize(new Dimension(30, 30));
        update();
    }

    void update() {
        if (game.hasFlag(vertical, horizontal)) {
            label.setText("F");
        } else if (!game.isRevealed(vertical, horizontal)) {
            label.setText("#");
        } else if (game.hasMine(vertical, horizontal)) {
            label.setText("*");
        } else if (game.nearMinesCount(vertical, horizontal) != 0) {
            var count = Integer.toString(game.nearMinesCount(vertical, horizontal));
            label.setText(count);
        } else {
            label.setText("-");
        }
    }
}