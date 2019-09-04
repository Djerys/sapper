package ui;

import controller.GameController;

import javax.swing.*;
import java.awt.*;

public class GameStatePanel extends JPanel {
    private final GameController controller;
    private final JLabel label;

    public GameStatePanel(GameController controller) {
        this.controller = controller;
        label = new JLabel(controller.getGame().getState().toString());
        add(label);
        setBorder(BorderFactory.createLoweredBevelBorder());

        setLayout(new FlowLayout());
    }
}
