package ui;

import game_logic.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FieldPanel extends JPanel {
    private final Game game;

    private CellPanel[][] cellPanels;

    private static class CellClickListener extends MouseAdapter {
        private final int vertical;
        private final int horizontal;
        private final Game game;

        CellClickListener(Game game, int vertical, int horizontal) {
            this.game = game;
            this.vertical = vertical;
            this.horizontal = horizontal;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                game.reveal(vertical, horizontal);
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                game.toggleFlag(vertical, horizontal);
            }
            if (game.isEnd()) {
                game.end();
            }
        }
    }

    FieldPanel(Game game) {
        this.game = game;
        setBackground(new Color(128, 132, 134));
        rebuildCells();
    }

    void rebuildCells() {
        int verticalSize = game.getVerticalSize();
        int horizontalSize = game.getHorizontalSize();
        cellPanels = new CellPanel[verticalSize][horizontalSize];

        for (int i = 0; i < verticalSize; i++) {
            for (int j = 0; j < horizontalSize; j++) {
                var cellPanel = new CellPanel(game, i, j);
                cellPanel.addMouseListener(new CellClickListener(game, i, j));
                add(cellPanel);
                cellPanels[i][j] = cellPanel;
            }
        }
    }

    void updateCell(int vertical, int horizontal) {
        cellPanels[vertical][horizontal].update();
    }

    void updateAllCells() {
        for (var row : cellPanels) {
            for (var cellPanel : row) {
                cellPanel.update();
            }
        }
    }
}
