package ui.gui;

import controller.GameController;
import ui.UI;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame implements UI {
    private GameController controller = null;
    private JPanel fieldPanel;

    @Override
    public void updateField() {
        fieldPanel.repaint();
    }

    @Override
    public void setController(GameController controller) {
        this.controller = controller;
        fieldPanel = new FieldPanel(controller.getGame());
        add(fieldPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setResizable(false);
        pack();
        setTitle("MineSweeper");
        setVisible(true);
        setLocation(new Point(400, 350));
    }
}
