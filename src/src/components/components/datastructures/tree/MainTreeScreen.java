package src.components.components.datastructures.tree;

import src.Config;
import src.components.base.Button;
import src.components.components.AbstractScreen;
import src.components.components.datastructures.tree.binarysearchtree.BinarySearchTreeScreen;

public class MainTreeScreen extends AbstractScreen {

    @Override
    public void addButtons() {

        int numberButtonPerColumn = 2;    
        int numberButtonPerRow = 1;
        int buttonWidth = 250;
        int buttonHeight = 50;
        int gapHeight = 40;
        int gapWidth = 40;

        int totalHeight = buttonHeight * numberButtonPerColumn
                + (numberButtonPerColumn - 1) * gapHeight;

        int totalWidth = buttonWidth * numberButtonPerRow
                + (numberButtonPerRow - 1) * gapWidth;

        int initialY = (Config.HEIGHT - totalHeight) / 2;
        int initialX = (Config.WIDTH - totalWidth) / 2;

         buttons = new Button[2];

        // Back
        buttons[0] = new Button(
                initialX,
                initialY + (gapHeight + buttonHeight),
                buttonWidth, buttonHeight,
                "Back"
        );

        // Binary Search Tree
        buttons[1] = new Button(
                initialX,
                initialY,
                buttonWidth, buttonHeight,
                "Binary Search Tree"
        );

        for (Button button : buttons) add(button);
    }

    @Override
    public void createDefaultScreens() {
        // Size updated
        screens = new AbstractScreen[2];
    }

    @Override
    public void addActionListenerForButtons() {

        // Back
        buttons[0].addActionListener(e -> {
            if (screens[0] == null) {
                screens[0] = getApp().getScreens().get("MainDataStructuresScreen");
            }
            setHidden(true);
            screens[0].setHidden(false);
        });

        // Binary Search Tree
        buttons[1].addActionListener(e -> {
            if (screens[1] == null) {
                screens[1] = new BinarySearchTreeScreen();
                screens[1].setVisible(false);
                getApp().addScreen(screens[1]);
            }
            setHidden(true);
            screens[1].setHidden(false);
        });
    }
}
