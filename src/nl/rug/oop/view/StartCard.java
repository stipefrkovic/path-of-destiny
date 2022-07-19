package nl.rug.oop.view;

import nl.rug.oop.controller.Controller;

import javax.swing.*;
import java.awt.*;

/**
 * Class StartCard which is an extension of Card.
 * It has a GridBagLayout and a start button which launches the game.
 * @author sfrkovic
 */
public class StartCard extends Card {
    /**
     * Constructor for StarCard calls Card constructor with a GridBagLayout and given parameter values.
     * @param font Font for StartCard
     * @param backgroundColor background Color for StartCard
     * @param foregroundColor foreground Color for StartCard
     * @param controller Controller for buttons in StartCard
     */
    public StartCard(Font font, Color backgroundColor, Color foregroundColor, Controller controller) {
        super(new GridBagLayout(), font, backgroundColor, foregroundColor, controller);
    }

    /**
     * Sets up the StartCard: creates and adds a start button.
     */
    @Override
    public void setup() {
        JButton startButton = new DepthButton("Start game", font, backgroundColor, controller);
        add(startButton);
    }
}
