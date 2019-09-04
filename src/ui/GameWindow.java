package ui;

import controller.GameController;

import javax.swing.*;
import java.awt.*;

public class GameWindow implements GameUI {
    private JFrame frame = new JFrame();
    private BoardPanel boardPanel;
    private GameStatePanel gameStatePanel;

    @Override
    public JFrame getFrame() {
        return frame;
    }

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
        frame.setJMenuBar(menuBar);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        frame.add(mainPanel);

        gameStatePanel = new GameStatePanel(controller);
        mainPanel.add(gameStatePanel, BorderLayout.NORTH);

        boardPanel = new BoardPanel(controller.getGame());
        mainPanel.add(boardPanel, BorderLayout.CENTER);


        frame.setResizable(true);
        frame.pack();
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
