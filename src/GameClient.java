import game_logic.Game;
import game_logic.Difficulty;
import ui.gui.GameFrame;
import ui.UI;

public class GameClient {
    public static void main(String[] args) {
        var game = new Game(Difficulty.BEGINNER);
        UI ui = new GameFrame(game);
        game.addFieldListener(ui::update);
        game.addEndListener(ui::update);
//        SwingUtilities.invokeLater(() -> new GameFrame(new Game(Difficulty.BEGINNER)));
    }
}
