package ui;

import game_logic.Game;
import game_logic.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FieldPanel extends JPanel {
    private final Game game;
    private final Image countImages[] = new Image[9];

    private static class MinesListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
        }
    }

    public FieldPanel(Game game) {
        this.game = game;
        initImages();
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
                g.drawImage(countImages[0], i * 30, j * 30, this);
            }
        }
    }
}
