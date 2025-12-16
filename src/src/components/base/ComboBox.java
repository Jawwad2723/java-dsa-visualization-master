package src.components.base;

import src.services.Theme;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

public class ComboBox<E> extends JComboBox<E> {
    private int x;
    private int y;
    private int width;
    private int height;
    private E[] choices;
    private int borderWidth;
    private Color borderColor;

    public ComboBox(int x, int y, int width, int height, E[] choices) {
        super(choices);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.choices = choices;
        this.borderWidth = Theme.BORDER_WIDTH;
        this.borderColor = Theme.BORDER_COLOR;

        setLayout(null);
        setSize(width, height);
        setBounds(x, y, width, height);
        setUI(new BasicComboBoxUI());
        setBorder(new LineBorder(borderColor, borderWidth, true));
        setBackground(Theme.BG_PANEL);
        setForeground(Theme.TEXT_PRIMARY);
        setFont(Theme.FONT_REGULAR);
        
        Component[] components = getComponents();
        for (Component component : components) {
            if (component instanceof AbstractButton) {
                AbstractButton button = (AbstractButton) component;
                button.setFocusPainted(false);
                button.setFont(Theme.FONT_REGULAR);
                button.setUI(new BasicButtonUI());
                button.setBackground(Theme.PRIMARY_BLUE);
                button.setForeground(Theme.TEXT_LIGHT);
                button.setBorder(new LineBorder(borderColor, borderWidth));
            }
        }

        setVisible(true);
    }
}
