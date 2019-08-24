package ui;

import game_logic.Game;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class GameButton extends JButton {
    private Game game;
    private final int vertical;
    private final int horizontal;

    GameButton(Game game, int vertical, int horizontal) {
        super("Press!");
        this.game = game;
        this.vertical = vertical;
        this.horizontal = horizontal;
        addMouseListener(new CellClickListener());
    }

    private class CellClickListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            if (e.getButton() == MouseEvent.BUTTON1) {
                game.tryRevealCell(vertical, horizontal);
                setText("O");
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                game.tryPutFlag(vertical, horizontal);
                game.tryRemoveFlag(vertical, horizontal);
                setText("F");
            }
        }
    }
}


