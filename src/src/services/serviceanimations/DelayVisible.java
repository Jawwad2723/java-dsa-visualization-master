 package src.services.serviceanimations;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class DelayVisible {
    private Timer timer;
    private final JComponent component;
    private final boolean visible;
    private final int delay;
    private final int duration;
    private long msStart;

    // Gradient / pink theme colors
    private final Color startColor = new Color(255, 192, 203, 0);   // transparent pink
    private final Color endColor   = new Color(255, 192, 203, 255); // solid pink

    public DelayVisible(JComponent component, boolean visible, int delay, int duration) {
        this.component = component;
        this.visible = visible;
        this.delay = delay;
        this.duration = duration;

        // Make component transparent for fade effect
        component.setOpaque(false);
        component.setVisible(false);
        component.setBackground(new Color(255, 255, 255, 0));
    }

    public void start() {
        timer = new Timer();
        msStart = System.currentTimeMillis() + delay;
        timer.schedule(new TaskAnimation(), delay, 15); // smooth 60fps-like update
    }

    public void stop() {
        component.setVisible(visible);
        timer.cancel();
        timer.purge();
        component.repaint();
    }

    // Animation task
    private class TaskAnimation extends TimerTask {
        @Override
        public void run() {
            long elapsed = System.currentTimeMillis() - msStart;
            double process = Math.min(1.0, (double) elapsed / duration);

            SwingUtilities.invokeLater(() -> {
                // ✅ Fixed alpha calculation
                float alpha = visible ? (float) process : 1.0f - (float) process;

                component.setVisible(true);

                // Gradient / alpha effect
                component.setBackground(new Color(
                        255, 182, 193, Math.min(255, (int) (alpha * 255))
                ));

                component.repaint();
            });

            if (process >= 1.0) {
                stop();
            }
        }
    }

    // Optional: paint gradient in component's paintComponent
    public static void paintGradientBackground(Graphics g, JComponent comp) {
        Graphics2D g2d = (Graphics2D) g.create();
        int w = comp.getWidth();
        int h = comp.getHeight();
        GradientPaint gp = new GradientPaint(
                0, 0, new Color(255, 240, 245),
                w, h, new Color(255, 182, 193)
        );
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
        g2d.dispose();
    }
}
