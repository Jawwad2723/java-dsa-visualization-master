package src.components.base;

import src.services.serviceanimations.TransitionColor;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button extends JButton {
    private int x;
    private int y;
    private int width;
    private int height;
    private TransitionColor tempTransitionColor;
    private Color backgroundColorEntered;
    private Color backgroundColorClicked;
    private Color backgroundColor;
    private int borderWidth;
    private Color borderColor;
    private boolean isClicked;
    private boolean hasStatusClicked = false;
    private int cornerRadius = 10;
    private int shadowSize = 4;

    public Button(int x, int y, int width, int height, String text) {
        super(text);
        setOpaque(false); // we paint custom background
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.backgroundColor = new Color(210, 210, 210);
        this.backgroundColorEntered = new Color(200, 255, 200);
        this.backgroundColorClicked = new Color(150, 150, 255);
        this.borderWidth = 2;
        this.borderColor = Color.BLACK;
        this.isClicked = false;

        setLayout(null);
        setSize(width, height);
        setBounds(x, y, width, height);
        setFocusPainted(false);
        setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
        setUI(new BasicButtonUI());
        addMouseListener(new MouseHandler());
        setBackground(new Color(210, 210, 210));
        setBorder(new LineBorder(
                borderColor, borderWidth
        ));
        setVisible(true);
    }

    @Override
    public void setBackground(Color background) {
        // keep our field in sync for custom painting and preserve behavior
        this.backgroundColor = background;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // shadow
        if (shadowSize > 0) {
            g2d.setColor(new Color(0, 0, 0, 60));
            g2d.fillRoundRect(2, 2, width - 4, height - 4, cornerRadius, cornerRadius);
        }

        // background gradient
        Color c1 = backgroundColor != null ? backgroundColor : new Color(210, 210, 210);
        Color c2 = c1.brighter();
        GradientPaint gp = new GradientPaint(0, 0, c1, 0, height, c2);
        g2d.setPaint(gp);
        g2d.fillRoundRect(0, 0, width, height, cornerRadius, cornerRadius);

        // border
        if (borderWidth > 0) {
            g2d.setColor(borderColor);
            g2d.setStroke(new BasicStroke(borderWidth));
            g2d.drawRoundRect(borderWidth/2, borderWidth/2, width - borderWidth, height - borderWidth, cornerRadius, cornerRadius);
        }

        // text
        g2d.setFont(getFont());
        FontMetrics fm = g2d.getFontMetrics();
        int x0 = (width - fm.stringWidth(getText())) / 2;
        int y0 = ((height - fm.getHeight()) / 2) + fm.getAscent();
        g2d.setColor(getForeground() != null ? getForeground() : Color.BLACK);
        g2d.drawString(getText(), x0, y0);

        g2d.dispose();
    }

    public void setBackgroundColorEntered(Color backgroundColorEntered) {
        this.backgroundColorEntered = backgroundColorEntered;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        setBorder(new LineBorder(
                borderColor, borderWidth
        ));
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        setBorder(new LineBorder(
                borderColor, borderWidth
        ));
    }

    public void setEnabledButton(boolean enable) {
        if (enable) {
            setBorderColor(Color.BLACK);
        } else {
            setBorderColor(Color.GRAY);
        }
        setEnabled(enable);
        setBackground(backgroundColor);
    }

    public void setIsClicked(boolean isClicked) {
        if (isEnabled() && hasStatusClicked) {
            this.isClicked = isClicked;
            if (isClicked) {
                setBackground(backgroundColorClicked);
            } else {
                setBackground(backgroundColor);
            }
        }
    }

    public void setHasStatusClicked(boolean hasStatusClicked) {
        this.hasStatusClicked = hasStatusClicked;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        repaint();
    }

    public void setShadowSize(int shadowSize) {
        this.shadowSize = shadowSize;
        repaint();
    }

    private Component getInstance() {
        return this;
    }

    private class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (hasStatusClicked) {
                if (tempTransitionColor != null) {
                    tempTransitionColor.stop();
                }
                if (getInstance().isEnabled()) {
                    isClicked = true;
                    tempTransitionColor = new TransitionColor(
                            getInstance(),
                            backgroundColorEntered,
                            backgroundColorClicked,
                            0, 300
                    );
                    tempTransitionColor.start();
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (tempTransitionColor != null) {
                tempTransitionColor.stop();
            }
            if (getInstance().isEnabled()) {
                if (isClicked) {
                    getInstance().setCursor(new Cursor(Cursor.HAND_CURSOR));
                    tempTransitionColor = new TransitionColor(
                            getInstance(),
                            backgroundColor,
                            backgroundColorClicked,
                            0, 300
                    );
                } else {
                    getInstance().setCursor(new Cursor(Cursor.HAND_CURSOR));
                    tempTransitionColor = new TransitionColor(
                            getInstance(),
                            backgroundColor,
                            backgroundColorEntered,
                            0, 300
                    );
                }
                tempTransitionColor.start();
            } else {
                getInstance().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                setBackground(backgroundColor);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (tempTransitionColor != null) {
                tempTransitionColor.stop();
            }
            if (getInstance().isEnabled()) {
                if (isClicked) {
                    tempTransitionColor = new TransitionColor(
                            getInstance(),
                            backgroundColorEntered,
                            backgroundColorClicked,
                            0, 300
                    );
                } else {
                    tempTransitionColor = new TransitionColor(
                            getInstance(),
                            backgroundColorEntered,
                            backgroundColor,
                            0, 300
                    );
                }
                tempTransitionColor.start();
            } else {
                setBackground(backgroundColor);
            }

        }
    }
}
