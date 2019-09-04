package ui;

import game_logic.Game;

import javax.swing.*;
import java.awt.*;

public class GameClient implements GameUI {
    private final Game game;
    private JFrame frame = new JFrame();
    private BoardPanel boardPanel;
    private GameStatePanel gameStatePanel;

    public GameClient(Game game) {
        this.game = game;
    }

    @Override
    public void start() {
        JMenuBar menuBar = new GameMenuBar(game);
        frame.setJMenuBar(menuBar);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        frame.add(mainPanel);

        gameStatePanel = new GameStatePanel(game);
        mainPanel.add(gameStatePanel, BorderLayout.NORTH);

        boardPanel = new BoardPanel(game);
        mainPanel.add(boardPanel, BorderLayout.CENTER);


        frame.setResizable(false);
        frame.pack();
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        addGameListeners();
    }

    private void addGameListeners() {
        game.addBoardListener(boardPanel::repaint);
        game.addBoardListener(gameStatePanel::update);
        game.addRestartListener(() -> {
            boardPanel.update();
            frame.pack();
        });
        // TODO: add end of the game sound
    }

}
