import game_logic.GameField;
import game_logic.GameDifficulty;

class MyClass {
    public int x;
    public int y;
}

public class Client {
    public static void main(String[] args) {
        var field = new GameField(GameDifficulty.BEGINNER);
        field.initialize();

    }
}
