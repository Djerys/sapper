import game_logic.Game;
import game_logic.GameDifficulty;
import game_logic.GameState;

import java.util.Scanner;

public class GameTest {
    public static void main(String[] args) {
        var game = new Game(GameDifficulty.BEGINNER);
        var in = new Scanner(System.in);
        while (game.getState() != GameState.LOSS && game.getState() != GameState.WIN) {
            game.printField();
            System.out.println();
            System.out.print("Введите координаты поля: ");
            var vertical = in.nextInt();
            var horizontal = in.nextInt();
            var command = in.next();
            switch (command) {
                case "reveal":
                    game.revealCellOnField(vertical, horizontal);
                    break;

                case "put":
                    game.putFlagOnField(vertical, horizontal);
                    break;

                case "remove":
                    game.removeFlagFromField(vertical, horizontal);
            }
            System.out.println();
        }
    }
}
