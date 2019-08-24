package ui.event;

import game_logic.Game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CellClickListener extends MouseAdapter {
    private final int vertical;
    private final int horizontal;

    private Game game;

    public CellClickListener(Game game, int vertical, int horizontal) {
        this.game = game;
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            game.tryRevealCell(vertical, horizontal);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            game.tryPutFlag(vertical, horizontal);
            game.tryRemoveFlag(vertical, horizontal);
        }
    }
}
