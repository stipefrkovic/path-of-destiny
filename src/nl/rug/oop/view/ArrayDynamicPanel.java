package nl.rug.oop.view;

import nl.rug.oop.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Class ArrayDynamicPanel which is an extension of DynamicPanel.
 * It adds new components as an array.
 * @author sfrkovic
 */
public class ArrayDynamicPanel extends DynamicPanel {
    /**
     * Constructor for ArrayDynamicPanel calls DynamicPanel constructor with give parameter values.
     * @param layout LayoutManager for ArrayDynamicPanel
     * @param font Font for ArrayDynamicPanel
     * @param backgroundColor background Color for ArrayDynamicPanel
     * @param foregroundColor foreground Color for ArrayDynamicPanel
     */
    public ArrayDynamicPanel(LayoutManager layout, Font font, Color backgroundColor, Color foregroundColor, Controller controller) {
        super(layout, font, backgroundColor, foregroundColor, controller);
    }

    /**
     * Removes all components and creates new ones (buttons) based on an ArrayList<String> of buttons.
     * @param buttons ArrayList<String> of buttons to be added
     */
    public void createButtons(ArrayList<String> buttons) {
        removeAll();
        if (buttons != null) {
            for (String b : buttons) {
                JButton button = new DepthButton(b, font, backgroundColor, controller);
                add(button);
            }
        }
    }

    /**
     * Removes all components and creates new ones (labels with icons and tooltips) based on the given parameters.
     * @param icons ArrayList<String> containing the URL names of icons to be fetched for the labels
     * @param toolTips ArrayList<String> containing the tool tips of the labels
     */
    public void createLabels(ArrayList<String> icons, ArrayList<String> toolTips) {
        removeAll();
        if (icons != null && toolTips != null && icons.size() == toolTips.size()) {
            for (int i=0; i<icons.size(); i++) {
                // NullPointerException is thrown if the resource can't be found
                ImageIcon icon = new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/" + icons.get(i) + ".png")));
                JLabel label = new JLabel(icon);
                label.setToolTipText(toolTips.get(i));
                add(label);
            }
        }
    }

}
