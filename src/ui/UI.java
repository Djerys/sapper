package ui;

import controller.GameController;

import javax.swing.*;

public interface UI {
    void initialize(GameController controller);
    int getCellSize();
    JPanel getBoardPanel();
    JPanel getGameStatePanel();
}
