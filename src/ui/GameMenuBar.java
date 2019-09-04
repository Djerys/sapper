package ui;

import controller.GameController;
import game_logic.Difficulty;

import javax.swing.*;


public class GameMenuBar extends JMenuBar {
    public GameMenuBar(GameController controller) {
        JMenu menu;
        JMenuItem menuItem;
        JRadioButtonMenuItem rbMenuItem;

        menu = new JMenu("Game");
        add(menu);

        menuItem = new JMenuItem("New");
        menuItem.addActionListener(e -> controller.getGame().restart());
        menu.add(menuItem);

        menu.addSeparator();

        ButtonGroup group = new ButtonGroup();

        rbMenuItem = new JRadioButtonMenuItem("Easy");
        rbMenuItem.addActionListener(e -> controller.getGame().restart(Difficulty.BEGINNER));
        group.add(rbMenuItem);
        menu.add(rbMenuItem);

        rbMenuItem = new JRadioButtonMenuItem("Intermediate");
        rbMenuItem.addActionListener(e -> controller.getGame().restart(Difficulty.INTERMEDIATE));
        group.add(rbMenuItem);
        menu.add(rbMenuItem);

        rbMenuItem = new JRadioButtonMenuItem("Hard");
        rbMenuItem.addActionListener(e -> controller.getGame().restart(Difficulty.HARD));
        group.add(rbMenuItem);
        menu.add(rbMenuItem);

        group.add(rbMenuItem);
        menu.add(rbMenuItem);
    }
}
