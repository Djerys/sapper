import game_logic.Game;
import game_logic.GameState;

import java.util.Scanner;

public class GameTest {
    public static void main(String[] args) {
        var game = new Game();
        var in = new Scanner(System.in);
        while (game.getState() != GameState.LOSS && game.getState() != GameState.WIN) {
            game.printField();
            System.out.println();
            System.out.print("Введите координаты поля: ");
            var vertical = in.nextInt() - 1;
            var horizontal = in.nextInt() - 1;
            var command = in.next();
            switch (command) {
                case "reveal":
                    game.tryRevealCell(vertical, horizontal);
                    break;
                case "put":
                    game.tryPutFlag(vertical, horizontal);
                    break;
                case "remove":
                    game.tryRemoveFlag(vertical, horizontal);
            }
            System.out.println();
        }
        game.finish();
        game.printField();
    }
}
