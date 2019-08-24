package ui;

import game_logic.Game;

import javax.swing.*;
import java.awt.*;

public class GameFieldPanel extends JPanel {
    private Game game;

    GameFieldPanel(Game game) {
        super(new GridLayout(game.getVerticalSize(), game.getHorizontalSize()));
        this.game = game;
        addButtons();
    }

    private void addButtons() {
        for (int i = 0; i < game.getVerticalSize(); i++) {
            for (int j = 0; j < game.getHorizontalSize(); j++) {
                add(new GameButton(game, i, j));
            }
        }
    }
}
