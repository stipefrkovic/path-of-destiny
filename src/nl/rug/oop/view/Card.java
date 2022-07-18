package nl.rug.oop.view;

import nl.rug.oop.controller.Controller;

import javax.swing.*;
import java.awt.*;

abstract public class Card extends JPanel {
    protected final LayoutManager layout;
    protected final Font font;
    protected final Color color;
    protected final Controller controller;

    public Card(LayoutManager layout, Font font, Color color, Controller controller) {
        super(layout);

        this.layout = layout;
        this.font = font;
        this.color = color;
        this.controller = controller;

        setFont(font);
        setBackground(color);
    }

    abstract public void setup();

}
