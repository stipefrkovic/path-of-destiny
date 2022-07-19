package nl.rug.oop.view;

import nl.rug.oop.controller.Controller;

import javax.swing.*;
import java.awt.*;

/**
 * A DynamicPanel (JPanel) that removes all components and adds new ones when an update method is called.
 * @author sfrkovic
 */
public class DynamicPanel extends JPanel {
    /**
     * Primary font of DynamicPanel.
     */
    protected Font font;
    /**
     * Primary background color of DynamicPanel.
     */
    protected Color backgroundColor;
    /**
     * Primary foreground color of DynamicPanel.
     */
    protected Color foregroundColor;
    /**
     * Controller for the buttons in DynamicPanel.
     */
    protected Controller controller;

    /**
     * Constructor for DynamicPanel that calls the JPanel constructor and sets the DynamicPanel class variables.
     * @param layout LayoutManager for DynamicPanel
     * @param font Font for DynamicPanel
     * @param backgroundColor background Color for DynamicPanel
     * @param foregroundColor foreground Color for DynamicPanel
     */
    public DynamicPanel(LayoutManager layout, Font font, Color backgroundColor, Color foregroundColor, Controller controller) {
        super(layout);

        this.font = font;
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
        this.controller = controller;

        setBackground(backgroundColor);
    }
}
