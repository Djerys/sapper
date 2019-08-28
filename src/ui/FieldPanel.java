package ui;

import game_logic.Game;
import game_logic.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FieldPanel extends JPanel {
    private final Game game;

    private CellPanel[][] cellPanels;

    private static class CellClickListener extends MouseAdapter {
        private final Position position;
        private final Game game;

        CellClickListener(Game game, Position position) {
            this.game = game;
            this.position = position;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                game.reveal(position);
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                game.toggleFlag(position);
            }
            if (game.isEnd()) {
                game.end();
            }
        }
    }

    FieldPanel(Game game) {
        this.game = game;
        rebuildCells();
    }

    void rebuildCells() {
        int widthSize = game.getWidthSize();
        int heightSize = game.getHeightSize();
        cellPanels = new CellPanel[widthSize][heightSize];
        setLayout(new GridLayout(heightSize, widthSize));

        for (int i = 0; i < widthSize; i++) {
            for (int j = 0; j < heightSize; j++) {
                var position = new Position(i, j);
                var cellPanel = new CellPanel(game, position);
                cellPanel.addMouseListener(new CellClickListener(game, position));
                add(cellPanel);
                cellPanels[i][j] = cellPanel;
            }
        }
    }

    void updateCell(Position position) {
        cellPanels[position.getWidth()][position.getHeight()].update();
    }

    void updateAllCells() {
        for (var row : cellPanels) {
            for (var cellPanel : row) {
                cellPanel.update();
            }
        }
    }
}
