package ui.gui;

import game_logic.Game;
import game_logic.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FieldPanel extends JPanel {
    private final Game game;
    private final Image countImages[] = new Image[9];
    private final Image closedImage = new ImageIcon("img/closed_cell.png").getImage();
    private final Image mineImage = new ImageIcon("img/mine_cell.png").getImage();
    private final Image flagImage = new ImageIcon("img/flag_cell.png").getImage();

    private static final int CELL_SIZE = 30;

    public FieldPanel(Game game) {
        this.game = game;
        initImages();
        setPreferredSize(new Dimension(game.getWidthSize() * CELL_SIZE,
                game.getHeightSize() * CELL_SIZE));
    }

    private void initImages() {
        for (int i = 0; i < 9; i++) {
            var path = "img/" + i + "_cell.png";
            countImages[i] = new ImageIcon(path).getImage();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        for (int i = 0; i < game.getWidthSize(); i++) {
            for (int j = 0; j < game.getHeightSize(); j++) {
                var position = new Position(i, j);
                Image imageToDraw;
                if (game.hasFlag(position)) {
                    imageToDraw = flagImage;
                } else if (!game.isRevealed(position)) {
                    imageToDraw = closedImage;
                } else if (game.hasMine(position)) {
                    imageToDraw = mineImage;
                } else {
                    imageToDraw = countImages[game.nearMinesCount(position)];
                }
                g.drawImage(imageToDraw, i * CELL_SIZE, j * CELL_SIZE, this);
            }
        }
    }
}
