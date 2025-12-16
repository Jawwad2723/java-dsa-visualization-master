package src.components.base;

import src.services.Theme;




import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TextField extends JTextField {
    private int x;
    private int y;
    private int width;
    private int height;
    private int thickness;
    private int arcWidth, arcHeight;
    private boolean isFocused = false;
    private Color borderColorNormal;
    private Color borderColorFocused;

    public TextField(int x, int y, int width, int height, String initialValue, Color backgroundColor, int thickness, int arcWidth, int arcHeight) {
        super(initialValue, height);

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
        setFont(Theme.FONT_REGULAR);
        setDisabledTextColor(Theme.TEXT_SECONDARY);
        setForeground(Theme.TEXT_PRIMARY);
        setBackground(backgroundColor != null ? backgroundColor : Theme.BG_PANEL);
        setCaretColor(Theme.PRIMARY_BLUE);
        
        setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(borderColorNormal, thickness, true),
                        BorderFactory.createEmptyBorder(4, 8, 4, 8)
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
                        BorderFactory.createEmptyBorder(4, 8, 4, 8)
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
        
        // Draw background with rounded corners
        g2d.setColor(getBackground());
        g2d.fillRoundRect(thickness - 1, thickness - 1, getWidth() - 2 * thickness, getHeight() - 2 * thickness, arcWidth, arcHeight);
        
        g2d.dispose();
        super.paintComponent(g);
    }

    public void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(0, 0, 0));
        g2.setStroke(new BasicStroke(thickness));
        g.drawRoundRect(thickness - 1, thickness - 1,
                getWidth() - 2 * thickness, getHeight() - 2 * thickness,
                arcWidth, arcHeight);
    }
}
