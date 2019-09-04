package ui;

import controller.GameController;

import javax.swing.*;

public interface GameUI {
    void initialize(GameController controller);
    int getCellSize();
    JPanel getBoardPanel();
    JPanel getGameStatePanel();
    JFrame getFrame();
}
