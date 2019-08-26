package ui;

import game_logic.Game;
import ui.event.CellClickListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FieldPanel extends JPanel {
    private Game game;
    private CellPanel[][] cellPanels;

    public FieldPanel(Game game) {
        this.game = game;
        setBackground(new Color(128, 132, 134));
        rebuildCells();
    }

    public void rebuildCells() {
        int verticalSize = game.getVerticalSize();
        int horizontalSize = game.getHorizontalSize();
        cellPanels = new CellPanel[verticalSize][horizontalSize];

        for (int i = 0; i < verticalSize; i++) {
            for (int j = 0; j < horizontalSize; j++) {
                cellPanels[i][j] = new CellPanel(game, i, j);
                cellPanels[i][j].addMouseListener(new CellClickListener(game, this, i, j));
                add(cellPanels[i][j]);
            }
        }
    }

    public void updateCell(int vertical, int horizontal) {
        cellPanels[vertical][horizontal].updateCell();
    }

    public void updateAll() {
        for (var row : cellPanels) {
            for (var cellPanel : row) {
                cellPanel.updateCell();
            }
        }
    }
}
