import game_logic.Game;
import game_logic.GameDifficulty;
import ui.GameFrame;

import javax.swing.*;

public class GameClient {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameFrame(new Game(GameDifficulty.BEGINNER)));
    }
}
