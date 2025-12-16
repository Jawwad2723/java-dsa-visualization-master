package src.services;

import java.awt.*;

/**
 * Theme class provides centralized color and font definitions for a modern, polished UI.
 * Uses a cool modern palette with subtle shadows and smooth transitions.
 */
public class Theme {
    // Primary colors - cool blue palette
    public static final Color PRIMARY_BLUE = new Color(41, 128, 185);      // Main blue
    public static final Color PRIMARY_LIGHT_BLUE = new Color(52, 152, 219); // Lighter blue
    public static final Color ACCENT_TEAL = new Color(26, 188, 156);       // Teal accent

    // Background colors
    public static final Color BG_LIGHT = new Color(236, 240, 241);          // Light background
    public static final Color BG_DARK = new Color(52, 73, 94);              // Dark background
    public static final Color BG_PANEL = new Color(255, 255, 255);          // White panels

    // State colors
    public static final Color STATE_HOVER = new Color(52, 152, 219);        // Hover state
    public static final Color STATE_ACTIVE = new Color(41, 128, 185);       // Active/clicked state
    public static final Color STATE_DISABLED = new Color(189, 195, 199);    // Disabled state

    // Text colors
    public static final Color TEXT_PRIMARY = new Color(44, 62, 80);         // Dark text
    public static final Color TEXT_SECONDARY = new Color(127, 140, 141);    // Gray text
    public static final Color TEXT_LIGHT = new Color(236, 240, 241);        // Light text

    // Border colors
    public static final Color BORDER_COLOR = new Color(189, 195, 199);      // Subtle border
    public static final Color BORDER_HIGHLIGHT = new Color(52, 152, 219);   // Highlighted border

    // Shadow color
    public static final Color SHADOW_COLOR = new Color(0, 0, 0);            // Black shadow (with alpha)

    // Fonts
    private static final String FONT_FAMILY = Font.SANS_SERIF;
    public static final Font FONT_TITLE = new Font(FONT_FAMILY, Font.BOLD, 20);
    public static final Font FONT_HEADING = new Font(FONT_FAMILY, Font.BOLD, 14);
    public static final Font FONT_REGULAR = new Font(FONT_FAMILY, Font.PLAIN, 12);
    public static final Font FONT_BUTTON = new Font(FONT_FAMILY, Font.BOLD, 13);
    public static final Font FONT_SMALL = new Font(FONT_FAMILY, Font.PLAIN, 10);

    // Sizing constants
    public static final int CORNER_RADIUS = 8;           // Rounded corner radius
    public static final int SHADOW_SIZE = 4;             // Drop shadow size
    public static final int BORDER_WIDTH = 2;            // Standard border width
    public static final int BORDER_WIDTH_THICK = 3;      // Thick border

    // Animation durations (milliseconds)
    public static final int ANIM_DURATION_SHORT = 150;   // Quick animations
    public static final int ANIM_DURATION_NORMAL = 300;  // Standard animations
    public static final int ANIM_DURATION_LONG = 500;    // Longer animations

    /**
     * Creates a gradient paint from top color to bottom color.
     */
    public static GradientPaint createVerticalGradient(int x, int y, int width, int height,
                                                        Color topColor, Color bottomColor) {
        return new GradientPaint(x, y, topColor, x, y + height, bottomColor);
    }

    /**
     * Creates a semi-transparent color with the given alpha (0-255).
     */
    public static Color withAlpha(Color color, int alpha) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }

    /**
     * Lightens a color by increasing RGB values.
     */
    public static Color lighten(Color color, int amount) {
        return new Color(
                Math.min(255, color.getRed() + amount),
                Math.min(255, color.getGreen() + amount),
                Math.min(255, color.getBlue() + amount)
        );
    }

    /**
     * Darkens a color by decreasing RGB values.
     */
    public static Color darken(Color color, int amount) {
        return new Color(
                Math.max(0, color.getRed() - amount),
                Math.max(0, color.getGreen() - amount),
                Math.max(0, color.getBlue() - amount)
        );
    }
}
