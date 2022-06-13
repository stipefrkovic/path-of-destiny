package nl.rug.oop.view;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Romain Guy
 */

public class DepthButton extends JButton {

    /** Creates a new instance of DepthButton */
    public DepthButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        GradientPaint p;
        p = new GradientPaint(0, 0, new Color(0xc0c0c0), 0, getHeight(), getBackground());

        Paint oldPaint = g2.getPaint();
        g2.setPaint(p);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setPaint(oldPaint);

        super.paintComponent(g);
    }
}
