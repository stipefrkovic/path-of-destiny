package nl.rug.oop.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DictionaryPanel extends Panel {

    public DictionaryPanel(LayoutManager layout, Font font, Color backgroundColor, Color foregroundColor) {
        super(layout, font, backgroundColor, foregroundColor);
    }

    @Override
    public void updateComponents(HashMap<String, String> components) {
        removeAll();
        for (Map.Entry<String, String> entry : components.entrySet()) {
            JLabel keyLabel = new JLabel();
            keyLabel.setIcon(new ImageIcon(GameView.class.getResource("resources/" + entry.getKey() + ".gif")));
            keyLabel.setToolTipText(entry.getKey());
            add(keyLabel);
            JLabel valueLabel = new JLabel();
            valueLabel.setText(String.valueOf(entry.getValue()));
            valueLabel.setFont(font);
            valueLabel.setForeground(Color.LIGHT_GRAY);
            add(valueLabel);
        }
    }

    @Override
    public void updateComponents(ArrayList<JComponent> components) {

    }

}
