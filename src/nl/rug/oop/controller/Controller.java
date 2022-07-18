package nl.rug.oop.controller;

import nl.rug.oop.model.RpgGame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The game controller which executes appropriate actions based on a command it receives.
 * @author Andro Erdelez
 */
public class Controller implements ActionListener {
    /**
     * The main game.
     */
    private RpgGame game;

    /**
     * A constructor which stores a given game.
     * @param game Game that needs to be controlled.
     */
    public Controller(RpgGame game) {
        this.game = game;
    }

    /**
     * An overridden method that executes an action that is sent to the game model based on the action command it
     * receives.
     * @param e Event to be processed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case "Start game":
                game.startGame();
                break;
            case "Load game":
                game.loadGame();
                break;
            case "Save game":
                RpgGame.saveGame(game);
                break;
            default:
                game.doAction(action);
                break;
        }
    }
}
