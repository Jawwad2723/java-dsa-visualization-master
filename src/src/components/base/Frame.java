package src.components.base;

import src.Config;
import src.services.Theme;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    public Frame(String title, int x, int y, int width, int height, Color backgroundColor, Font font) {
        super(title);
        setSize(width, height);
        setResizable(false);
        setAlwaysOnTop(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        // Use modern background color if not specified
        Color bg = backgroundColor != null ? backgroundColor : Theme.BG_LIGHT;
        getContentPane().setBackground(bg);
        
        setFont(font != null ? font : Theme.FONT_REGULAR);
        setLocation(x, y);
        setVisible(true);
    }
}
