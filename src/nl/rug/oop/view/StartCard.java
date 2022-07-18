package nl.rug.oop.view;

import nl.rug.oop.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class StartCard extends Card {

    public StartCard(Font font, Color color, Controller controller) {
        super(new GridBagLayout(), font, color, controller);
    }

    @Override
    public void setup() {
        JButton startButton = new DepthButton("Start game", font, color, controller);
        add(startButton);
    }
}
