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
        game.doAction(e.getActionCommand());
    }
}
