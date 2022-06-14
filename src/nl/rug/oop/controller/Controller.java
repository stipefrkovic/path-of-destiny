package nl.rug.oop.controller;

import nl.rug.oop.model.RpgGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private RpgGame game;

    public Controller(RpgGame game) {
        this.game = game;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case "Start":
                game.startGame();
            case "Load":
                game.loadGame();
            case "Save":
                RpgGame.saveGame(game);
            default:
                game.doAction(action);
        }
    }
}
