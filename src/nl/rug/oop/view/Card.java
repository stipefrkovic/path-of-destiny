package nl.rug.oop.view;

import nl.rug.oop.controller.Controller;

import javax.swing.*;
import java.awt.*;

/**
 * A Card (JPanel) meant for a JFrame with a CardLayout.
 * @author sfrkovic
 */
abstract public class Card extends JPanel {
    /**
     * Primary font of Card.
     */
    protected Font font;
    /**
     * Primary background color of Card.
     */
    protected Color backgroundColor;
    /**
     * Primary foreground color of Card.
     */
    protected Color foregroundColor;
    /**
     * Controller for the buttons in Card.
     */
    protected Controller controller;

    /**
     * Constructor for Card that calls the JPanel constructor and sets Card class variables.
     * @param layout LayoutManager for the Card
     * @param font Font for the Card
     * @param backgroundColor background Color for the Card
     * @param foregroundColor foreground Color for the Card
     * @param controller Controller for the buttons in the Card
     */
    public Card(LayoutManager layout, Font font, Color backgroundColor, Color foregroundColor, Controller controller) {
        super(layout);

        this.font = font;
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
        this.controller = controller;

        setFont(font);
        setBackground(backgroundColor);
    }

    /**
     * Initializes the elements of an instance of a Card.
     */
    abstract public void setup();

}
