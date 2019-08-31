package ui;

import game_logic.Game;
import game_logic.Position;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class CellPanel extends JPanel {
    private final Position position;

    private Game game;

    private JLabel label = new JLabel();

    CellPanel(Game game, Position position) {
        this.game = game;
        this.position = position;
        label.setSize(new Dimension(20, 20));
        add(label);
        setPreferredSize(new Dimension(30, 30));
        setMinimumSize(new Dimension(30, 30));
        update();
    }

    void update() {
        if (game.hasFlag(position)) {
            label.setText("F");
        } else if (!game.isRevealed(position)) {
//            label.setText("#");
            label.setIcon(getImageIcon("closed_cell.png"));
        } else if (game.hasMine(position)) {
//            label.setText("*");
            label.setIcon(getImageIcon("mine_cell.png"));
        } else if (game.nearMinesCount(position) != 0) {
            var count = Integer.toString(game.nearMinesCount(position));
//            label.setText(count);
            var path = count + "_cell.png";
            label.setIcon(getImageIcon(path));
        } else {
//            label.setText("-");
            label.setIcon(getImageIcon("revealed_cell.png"));
        }
    }

    private ImageIcon getImageIcon(String path) {
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image scaledImage = bufferedImage.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}