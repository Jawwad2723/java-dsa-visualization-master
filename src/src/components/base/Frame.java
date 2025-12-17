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
        
        JPanel contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (Config.TREE_BACKGROUND != null) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                    Image img = Config.TREE_BACKGROUND.getImage();
                    g2d.drawImage(img, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        contentPane.setLayout(null);
        
        Color bg = backgroundColor != null ? backgroundColor : Theme.BG_LIGHT;
        contentPane.setBackground(bg);
        
        setContentPane(contentPane);
        
        setFont(font != null ? font : Theme.FONT_REGULAR);
        setLocation(x, y);
        setVisible(true);
    }
}
