import game_logic.Difficulty;
import game_logic.Game;
import ui.GameClient;
import ui.GameUI;

public class Main {
    public static void main(String[] args) {
        var game = new Game(Difficulty.INTERMEDIATE);
        GameUI ui = new GameClient(game);
        ui.start();
    }
}
