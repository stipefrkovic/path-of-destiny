package nl.rug.oop.view;

import nl.rug.oop.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Class DictionaryDynamicPanel which is an extension of DynamicPanel.
 * It adds new components as dictionary-like key-value pairs.
 * @author sfrkovic
 */
public class DictionaryDynamicPanel extends DynamicPanel {
    /**
     * Constructor for DictionaryDynamicPanel calls DynamicPanel constructor with give parameter values.
     * @param layout LayoutManager for DictionaryDynamicPanel
     * @param font Font for DictionaryDynamicPanel
     * @param backgroundColor background Color for DictionaryDynamicPanel
     * @param foregroundColor foreground Color for DictionaryDynamicPanel
     */
    public DictionaryDynamicPanel(LayoutManager layout, Font font, Color backgroundColor, Color foregroundColor, Controller controller) {
        super(layout, font, backgroundColor, foregroundColor, controller);
    }

    /**
     * Removes all components and creates new ones (key-value pairs of labels) based on the given parameters.
     * It creates a key label with an icon (URL name obtained from the key string)
     *  and creates a value label with text/value obtained from the value string.
     * @param components HashMap<String, String> containing the key and value pairs of components to be created
     */
    public void createComponents(HashMap<String, String> components) {
        removeAll();
        for (Map.Entry<String, String> entry : components.entrySet()) {
            JLabel keyLabel = new JLabel();
            // NullPointerException is thrown if the resource can't be found
            keyLabel.setIcon(new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/" + entry.getKey() + ".gif"))));
            keyLabel.setToolTipText(entry.getKey());
            add(keyLabel);
            JLabel valueLabel = new JLabel();
            valueLabel.setText(String.valueOf(entry.getValue()));
            valueLabel.setFont(font);
            valueLabel.setForeground(foregroundColor);
            add(valueLabel);
        }
    }


}
