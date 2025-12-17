package src.components.base;

import src.services.Theme;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TextArea extends JTextArea {
    private int x;
    private int y;
    private int width;
    private int height;
    private int thickness;
    private int arcWidth, arcHeight;
    private boolean isFocused = false;
    private Color borderColorNormal;
    private Color borderColorFocused;

    public TextArea(int x, int y, int width, int height, int thickness, int arcWidth, int arcHeight) {
        super(width, height);

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.thickness = thickness;
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        this.borderColorNormal = Theme.BORDER_COLOR;
        this.borderColorFocused = Theme.PRIMARY_BLUE;

        setOpaque(false);
        setLayout(null);
        setSize(width, height);
        setBounds(x, y, width, height);
        setForeground(Theme.TEXT_PRIMARY);
        setBackground(Theme.BG_PANEL);
        setCaretColor(Theme.PRIMARY_BLUE);
        setBorder(new LineBorder(borderColorNormal, thickness, true));
        setLineWrap(true);
        setWrapStyleWord(true);
        setFont(Theme.FONT_REGULAR);
        setDisabledTextColor(Theme.TEXT_SECONDARY);
        setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(borderColorNormal, thickness, true),
                        BorderFactory.createEmptyBorder(5, 5, 5, 5)
                )
        );
        
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                isFocused = true;
                updateBorderColor();
            }

            @Override
            public void focusLost(FocusEvent e) {
                isFocused = false;
                updateBorderColor();
            }
        });
        
        setVisible(true);
    }

    private void updateBorderColor() {
        Color currentBorder = isFocused ? borderColorFocused : borderColorNormal;
        setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(currentBorder, thickness, true),
                        BorderFactory.createEmptyBorder(5, 5, 5, 5)
                )
        );
        repaint();
    }

    public void setBorderColorNormal(Color color) {
        this.borderColorNormal = color;
        if (!isFocused) updateBorderColor();
    }

    public void setBorderColorFocused(Color color) {
        this.borderColorFocused = color;
        if (isFocused) updateBorderColor();
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setColor(getBackground());
        g2d.fillRoundRect(thickness - 1, thickness - 1, getWidth() - 2 * thickness, getHeight() - 2 * thickness, arcWidth, arcHeight);
        
        g2d.dispose();
        super.paintComponent(g);
    }

    public void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(getForeground());
        g2.setStroke(new BasicStroke(thickness));
        g.drawRoundRect(thickness - 1, thickness - 1,
                getWidth() - 2 * thickness, getHeight() - 2 * thickness,
                arcWidth, arcHeight);
    }
}
