package ui;

import game_logic.Game;
import ui.event.CellClickListener;

import javax.swing.*;
import java.awt.*;

public class GameFieldPanel extends JPanel {
    private Game game;
    private JButton[][] buttons;

    GameFieldPanel(Game game) {
        super(new GridLayout(game.getVerticalSize(), game.getHorizontalSize()));
        this.game = game;
        this.buttons = new JButton[game.getVerticalSize()][game.getHorizontalSize()];
        addButtons();
    }

    private void addButtons() {
        for (int i = 0; i < game.getVerticalSize(); i++) {
            for (int j = 0; j < game.getHorizontalSize(); j++) {
                var button = new JButton();
                buttons[i][j] = new JButton();
                button.addMouseListener(new CellClickListener(game, i, j));
                add(button);
            }
        }
    }
}
