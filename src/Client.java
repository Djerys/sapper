import game_logic.GameField;
import game_logic.GameDifficulty;

public class Client {
    public static void main(String[] args) {
        var field = new GameField(GameDifficulty.BEGINNER);
        field.setupMines();
    }
}
