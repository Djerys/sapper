package ui.event;

import game_logic.Game;
import ui.FieldPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CellClickListener extends MouseAdapter {
    private final int vertical;
    private final int horizontal;
    private final FieldPanel panel;

    private Game game;

    public CellClickListener(Game game, FieldPanel panel, int vertical, int horizontal) {
        this.game = game;
        this.panel = panel;
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            game.reveal(vertical, horizontal);
            panel.updateAll();
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            game.toggleFlag(vertical, horizontal);
            panel.updateCell(vertical, horizontal);
        }
    }
}
