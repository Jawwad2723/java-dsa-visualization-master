package src.services.serviceanimations;

import src.components.base.Panel;

import java.util.Timer;
import java.util.TimerTask;

public class FadeTransition {
    private Timer timer;
    private final Panel panel;
    private final float startOpacity;
    private final float targetOpacity;
    private final int delay;
    private final int duration;
    private long msStart;

    public FadeTransition(Panel panel, float startOpacity, float targetOpacity, int delay, int duration) {
        this.panel = panel;
        this.startOpacity = clamp(startOpacity);
        this.targetOpacity = clamp(targetOpacity);
        this.delay = delay;
        this.duration = duration;
    }

    private float clamp(float v) {
        if (v < 0f) return 0f;
        if (v > 1f) return 1f;
        return v;
    }

    public void start() {
        timer = new Timer();
        msStart = System.currentTimeMillis() + delay;
        timer.schedule(new TaskAnimation(), delay, 1);
    }

    public void stop() {
        timer.cancel();
        timer.purge();
        panel.setOpacity(targetOpacity);
        if (panel.getParent() != null) panel.getParent().repaint();
    }

    private class TaskAnimation extends TimerTask {
        @Override
        public void run() {
            long temp = System.currentTimeMillis() - msStart;
            double process = temp * 1.0 / duration;
            if (process >= 1f) {
                stop();
                return;
            }
            float value = (float) (startOpacity + process * (targetOpacity - startOpacity));
            panel.setOpacity(value);
            if (panel.getParent() != null) panel.getParent().repaint();
        }
    }
}
