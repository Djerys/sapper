package ui;

import game_logic.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameFieldPanel {
    private JPanel fieldPanel;
    private Game game;

    public GameFieldPanel(Game game) {
        this.game = game;
        var layout = new GridLayout(game.getHorizontalFieldSize(), game.getVerticalFieldSize());
        fieldPanel = new JPanel(layout);
        setupButtons();
    }

    private void setupButtons() {
        for (int i = 0; i <= game.getVerticalFieldSize(); i++) {
            for (int j = 0; j <= game.getHorizontalFieldSize(); j++) {
                var button = new JButton();
                fieldPanel.add(button);
            }
        }
    }
}
