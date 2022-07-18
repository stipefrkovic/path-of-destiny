package nl.rug.oop.view;

import nl.rug.oop.controller.Controller;

import javax.swing.*;
import java.awt.*;

/**
 * DepthButton which is prettier of an extension of JButton
 * @author Romain Guy
 */

public class DepthButton extends JButton {

    /** Creates a new instance of DepthButton */
    public DepthButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
    }

    public DepthButton(String text, Font font, Color color, Controller controller) {
        this(text);
        setActionCommand(text);
        setFont(font);
        setBackground(color);
        addActionListener(controller);
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
