package nl.rug.oop.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

abstract public class DynamicPanel extends JPanel {
    protected final LayoutManager layout;
    protected final Font font;
    protected final Color backgroundColor;
    protected final Color foregroundColor;

    public DynamicPanel(LayoutManager layout, Font font, Color backgroundColor, Color foregroundColor) {
        super(layout);

        this.layout = layout;
        this.font = font;
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;

        setBackground(backgroundColor);
    }

    abstract public void updateComponents(HashMap<String, String> components);

    abstract public void updateComponents(ArrayList<JComponent> components);
}
