package ui;

import controller.GameController;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame implements UI {
    private BoardPanel boardPanel;
    private GameStatePanel gameStatePanel;

    @Override
    public JPanel getBoardPanel() {
        return boardPanel;
    }

    @Override
    public JPanel getGameStatePanel() {
        return gameStatePanel;
    }

    @Override
    public int getCellSize() {
        return boardPanel.getCellSize();
    }

    @Override
    public void initialize(GameController controller) {
        JMenuBar menuBar = new GameMenuBar(controller);
        setJMenuBar(menuBar);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        gameStatePanel = new GameStatePanel(controller);
        mainPanel.add(gameStatePanel, BorderLayout.NORTH);

        boardPanel = new BoardPanel(controller.getGame());
        mainPanel.add(boardPanel, BorderLayout.CENTER);


        setResizable(true);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
