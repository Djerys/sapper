import game_logic.Game;
import ui.GameFrame;

import javax.swing.*;

public class GameClient {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameFrame(new Game()));
    }
}
