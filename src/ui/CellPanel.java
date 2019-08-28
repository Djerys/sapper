package ui;

import game_logic.Game;
import game_logic.Position;

import javax.swing.*;
import java.awt.*;

class CellPanel extends JPanel {
    private final Position position;

    private Game game;

    private JLabel label = new JLabel();

    CellPanel(Game game, Position position) {
        this.game = game;
        this.position = position;
        add(label);
        setPreferredSize(new Dimension(30, 30));
        setMinimumSize(new Dimension(30, 30));
        update();
    }

    void update() {
        if (game.hasFlag(position)) {
            label.setText("F");
        } else if (!game.isRevealed(position)) {
            label.setText("#");
        } else if (game.hasMine(position)) {
            label.setText("*");
        } else if (game.nearMinesCount(position) != 0) {
            var count = Integer.toString(game.nearMinesCount(position));
            label.setText(count);
        } else {
            label.setText("-");
        }
    }
}