package src.components.components.datastructures;

import src.Config;
import src.components.base.Button;
import src.components.components.AbstractScreen;
import src.components.components.datastructures.list.MainListScreen;
import src.components.components.datastructures.queue.MainQueueScreen;
import src.components.components.datastructures.stack.MainStackScreen;
import src.components.components.datastructures.tree.MainTreeScreen;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainDataStructuresScreen extends AbstractScreen {

    // 🌸 PINK THEME COLORS
    private static final Color BTN_NORMAL = new Color(255, 182, 193); // light pink
    private static final Color BTN_HOVER  = new Color(255, 105, 180); // hot pink
    private static final Color BTN_TEXT   = new Color(80, 20, 60);    // dark pink
    private static final Color BG_COLOR   = new Color(255, 240, 245); // lavender blush

    public MainDataStructuresScreen() {
        super();
        setBackground(BG_COLOR);
    }

    @Override
    public void addButtons() {

        int numberButtonPerColumn = 5;
        int buttonWidth = 300;
        int buttonHeight = 60;
        int gapHeight = 30;

        int totalHeight = buttonHeight * numberButtonPerColumn
                + (numberButtonPerColumn - 1) * gapHeight;

        int initialY = (Config.HEIGHT - totalHeight) / 2;
        int initialX = (Config.WIDTH - buttonWidth) / 2;

        buttons = new Button[5];

        buttons[0] = new Button(initialX, initialY, buttonWidth, buttonHeight, "Back");
        buttons[1] = new Button(initialX, initialY + (gapHeight + buttonHeight), buttonWidth, buttonHeight, "List");
        buttons[2] = new Button(initialX, initialY + (gapHeight + buttonHeight) * 2, buttonWidth, buttonHeight, "Stack");
        buttons[3] = new Button(initialX, initialY + (gapHeight + buttonHeight) * 3, buttonWidth, buttonHeight, "Queue");
        buttons[4] = new Button(initialX, initialY + (gapHeight + buttonHeight) * 4, buttonWidth, buttonHeight, "Tree");

        for (Button button : buttons) {
            styleButton(button);
            add(button);
        }
    }

    // 🎨 Stylish pink buttons with smooth hover effect
    private void styleButton(Button button) {
        button.setBackground(BTN_NORMAL);
        button.setForeground(BTN_TEXT);
        button.setFont(Config.MONOSPACED_BOLD_16);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(BTN_HOVER);
                button.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(BTN_NORMAL);
                button.setForeground(BTN_TEXT);
            }
        });
    }

    @Override
    public void createDefaultScreens() {
        screens = new AbstractScreen[5];
    }

    @Override
    public void addActionListenerForButtons() {

        // Back
        buttons[0].addActionListener(e -> {
            if (screens[0] == null) {
                screens[0] = getApp().getScreens().get("MainScreen");
            }
            setHidden(true);
            screens[0].setHidden(false);
        });

        // List
        buttons[1].addActionListener(e -> {
            if (screens[1] == null) {
                screens[1] = new MainListScreen();
                screens[1].setVisible(false);
                getApp().addScreen(screens[1]);
            }
            setHidden(true);
            screens[1].setHidden(false);
        });

        // Stack
        buttons[2].addActionListener(e -> {
            if (screens[2] == null) {
                screens[2] = new MainStackScreen();
                screens[2].setVisible(false);
                getApp().addScreen(screens[2]);
            }
            setHidden(true);
            screens[2].setHidden(false);
        });

        // Queue
        buttons[3].addActionListener(e -> {
            if (screens[3] == null) {
                screens[3] = new MainQueueScreen();
                screens[3].setVisible(false);
                getApp().addScreen(screens[3]);
            }
            setHidden(true);
            screens[3].setHidden(false);
        });

        // Tree
        buttons[4].addActionListener(e -> {
            if (screens[4] == null) {
                screens[4] = new MainTreeScreen();
                screens[4].setVisible(false);
                getApp().addScreen(screens[4]);
            }
            setHidden(true);
            screens[4].setHidden(false);
        });
    }
}
