package ui;

import game_logic.Difficulty;
import game_logic.Game;

import javax.swing.*;


class GameMenuBar extends JMenuBar {
    GameMenuBar(Game game) {
        JMenu menu;
        JMenuItem menuItem;
        JRadioButtonMenuItem rbMenuItem;

        menu = new JMenu("Game");
        add(menu);

        menuItem = new JMenuItem("New");
        menuItem.addActionListener(e -> game.restart());
        menu.add(menuItem);

        menu.addSeparator();

        ButtonGroup group = new ButtonGroup();

        rbMenuItem = new JRadioButtonMenuItem("Easy");
        rbMenuItem.addActionListener(e -> game.restart(Difficulty.EASY));
        group.add(rbMenuItem);
        menu.add(rbMenuItem);

        rbMenuItem = new JRadioButtonMenuItem("Intermediate");
        rbMenuItem.addActionListener(e -> game.restart(Difficulty.INTERMEDIATE));
        group.add(rbMenuItem);
        menu.add(rbMenuItem);

        rbMenuItem = new JRadioButtonMenuItem("Hard");
        rbMenuItem.addActionListener(e -> game.restart(Difficulty.HARD));
        group.add(rbMenuItem);
        menu.add(rbMenuItem);

        rbMenuItem = new JRadioButtonMenuItem("Custom...");
        rbMenuItem.addActionListener(e1 -> {
            SelectDialogPanel dialogPanel = new SelectDialogPanel();
            int option = JOptionPane.showOptionDialog(this, dialogPanel, "Custom Board",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
            if (option == JOptionPane.OK_OPTION) {
                game.restart(new Difficulty(dialogPanel.getSelectedWidth(), dialogPanel.getSelectedHeight(),
                        dialogPanel.getSelectedMines()));
            }
        });
        group.add(rbMenuItem);
        menu.add(rbMenuItem);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);
    }
}
