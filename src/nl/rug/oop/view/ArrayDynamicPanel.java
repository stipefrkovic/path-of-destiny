package nl.rug.oop.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DynamicPanel extends Panel {


    public DynamicPanel(LayoutManager layout, Font font, Color backgroundColor, Color foregroundColor) {
        super(layout, font, backgroundColor, foregroundColor);
    }

    @Override
    public void updateComponents(ArrayList<JComponent> components) {
        removeAll();
        if (components != null) {
            for (JComponent c : components) {
                add(c);
            }
        }
    }

    @Override
    public void updateComponents(HashMap<String, String> components) {

    }

}
