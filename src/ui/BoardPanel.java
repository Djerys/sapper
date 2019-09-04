package ui;

import game_logic.Game;
import game_logic.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class BoardPanel extends JPanel {
    private final Game game;
    private final Image[] countImages = new Image[9];
    private final Image closedImage = new ImageIcon("img/closed_cell.png").getImage();
    private final Image mineImage = new ImageIcon("img/mine_cell.png").getImage();
    private final Image flagImage = new ImageIcon("img/flag_cell.png").getImage();
    private final static int CELL_SIZE = 30;

    private class BoardClickListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            int width = e.getX() / CELL_SIZE;
            int height = e.getY() / CELL_SIZE;
            var position = new Position(width, height);
            if (e.getButton() == MouseEvent.BUTTON1) {
                game.reveal(position);
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                game.toggleFlag(position);
            }
        }
    }

    BoardPanel(Game game) {
        this.game = game;
        initializeCountImages();
        update();
        addMouseListener(new BoardClickListener());;
    }

    void update() {
        int width = game.getWidthSize() * CELL_SIZE;
        int height = game.getHeightSize() * CELL_SIZE;
        setPreferredSize(new Dimension(width, height));
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

    private void initializeCountImages() {
        for (int i = 0; i < 9; i++) {
            var path = "img/" + i + "_cell.png";
            countImages[i] = new ImageIcon(path).getImage();
        }
    }
}
