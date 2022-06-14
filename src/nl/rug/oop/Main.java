package nl.rug.oop;

import nl.rug.oop.controller.Controller;
import nl.rug.oop.model.RpgGame;
import nl.rug.oop.view.GameView;

public class Main {
    public static void main(String[] args) {
        RpgGame game = new RpgGame();
        Controller controller = new Controller(game);
        GameView view = new GameView(controller);
        view.setup();
    }
}
