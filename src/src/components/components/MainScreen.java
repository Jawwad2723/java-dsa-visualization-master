 package src.components.components;

import src.Config;
import src.components.base.Button;
import src.components.components.datastructures.MainDataStructuresScreen;

public class MainScreen extends AbstractScreen {

    @Override
    public void addButtons() {

        int buttonWidth = 300;
        int buttonHeight = 60;

        int initialX = (Config.WIDTH - buttonWidth) / 2;
        int initialY = (Config.HEIGHT - buttonHeight) / 2;
 
        buttons = new Button[1];

        buttons[0] = new Button(
                initialX,
                initialY,
                buttonWidth,
                buttonHeight,
                "DATA STRUCTURES"
        );
 
        buttons[0].setBackground(Config.COLOR_BLUE);
        buttons[0].setForeground(Config.COLOR_WHITE);
        buttons[0].setFont(Config.MONOSPACED_BOLD_16);

        add(buttons[0]);
    }

    @Override
    public void createDefaultScreens() {
  
        screens = new AbstractScreen[1];
    }

    @Override
    public void addActionListenerForButtons() {

        buttons[0].addActionListener(e -> {

            if (screens[0] == null) {
                screens[0] = new MainDataStructuresScreen();
                screens[0].setVisible(false);
                getApp().addScreen(screens[0]);
            }

            setHidden(true);
            screens[0].setHidden(false);
        });
    }
}
