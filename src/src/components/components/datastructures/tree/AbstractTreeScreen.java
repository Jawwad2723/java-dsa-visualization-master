package src.components.components.datastructures.tree;

import java.awt.*;
import java.util.Map;
import src.Config;
import src.components.base.Button;
import src.components.base.Dialog;
import src.components.base.Panel;
import src.components.base.TextField;
import src.components.components.datastructures.AbstractDataStructureScreen;
import src.models.datastructures.queue.LinkedQueue;
import src.models.datastructures.queue.QueueInterface;
import src.models.datastructures.tree.BinarySearchTree;
import src.models.datastructures.tree.LinkedBinaryTree;

public abstract class AbstractTreeScreen extends AbstractDataStructureScreen {
    public static final Map<Integer, String> ACTIONS = Map.of(
            0, "traversal left-root-right (in-order)",
            1, "traversal root-left-right (pre-order)",
            2, "traversal left-right-root (post-order)",
            3, "traversal right-root-left",
            4, "traversal right-left-root",
            5, "traversal root-right-left",
            6, "insert(value)",
            7, "delete(value)",
            8, "height()",
            9, "numberOfNodes()"
    );
    protected int value = -1;
    public BinarySearchTree<TreePanelNode> tree;

    public AbstractTreeScreen() {
        super();
    }

    public AbstractViewTreeAction getViewAction() {
        return (AbstractViewTreeAction) viewAction;
    }

    public abstract void createTree();


    @Override
    public void createViewController() {
        createTree();
        viewController = new ViewTreeController(this);
        add(viewController);
    }

    @Override
    public void setIndexActionSelected(int indexActionSelected) {
        this.indexActionSelected = indexActionSelected;
        // Only insert/delete require an input value
        if (indexActionSelected == 6 || indexActionSelected == 7) {
            new DialogGetValue(
                    (Config.WIDTH - 300) / 2,
                    (Config.HEIGHT - 300) / 2,
                    300, 300
            );
        }
        if (indexActionSelected == -1) {
            viewController.buttons[1].setText("Choose Action");
            viewController.buttons[2].setEnabledButton(false);
        } else {
            viewController.buttons[1].setText(
                    ACTIONS.get(indexActionSelected)
                            .replace("value", "value=" + value)
            );
            viewController.buttons[2].setEnabledButton(true);
        }
    }

    @Override
    public void runAction() {
        setEnabledAllButtons(false);
        switch (indexActionSelected) {
            case 0, 1, 2, 3, 4, 5 -> getViewAction().actionTraversal(indexActionSelected);
            case 6 -> getViewAction().actionInsert(value);
            case 7 -> getViewAction().actionDelete(value);
            case 8 -> {
                DialogShowResult.show(
                        "Tree Height",
                        "Height of tree = " + tree.height(tree.root())
                );
                setEnabledAllButtons(true);
            }
            case 9 -> {
                DialogShowResult.show(
                        "Number of Nodes",
                        "Number of nodes = " + tree.size()
                );
                setEnabledAllButtons(true);
            }
            default -> {}
        }
    }

    public TreePanelNode[] getPanelNodeArray() {
        TreePanelNode[] array = new TreePanelNode[AbstractViewTreeAction.INDEX_COLUMNS.length];
        int i = 0;
        LinkedBinaryTree.Node<TreePanelNode> node = tree.root;
        QueueInterface<LinkedBinaryTree.Node<TreePanelNode>> queue = new LinkedQueue<>();
        queue.enqueue(node);
        while (i < AbstractViewTreeAction.INDEX_COLUMNS.length) {
            if (queue.first() == null) {
                queue.enqueue(null);
                queue.enqueue(null);
                array[i++] = null;
                queue.dequeue();
            } else {
                queue.enqueue(queue.first().left);
                queue.enqueue(queue.first().right);
                array[i] = queue.dequeue().element;
                array[i].setXY(
                        AbstractViewTreeAction.getDefaultX(i),
                        AbstractViewTreeAction.getDefaultY(i)
                );
                i++;
            }
        }
        return array;
    }

    private class DialogGetValue extends DialogWithFieldText {
        public DialogGetValue(int x, int y, int width, int height) {
            super(x, y, width, height, "Set value in range [0-99]");
        }

        @Override
        public void addListeners() {
            button.addActionListener(e -> {
                String data = textField.getText();
                if (data.matches("[0-9]+")) {
                    value = Integer.parseInt(data);
                    if (value > 99) value = 99;
                } else {
                    value = 0;
                }
                dialog.dispose();
            });
        }
    }

    public static class DialogShowResult extends Dialog {
        private static String currentMessage;

        private DialogShowResult(String title) {
            super((Config.WIDTH - Config.WIDTH / 3 * 2) / 2,
                    (Config.HEIGHT - Config.HEIGHT / 4) / 2,
                    Config.WIDTH / 3 * 2,
                    Config.HEIGHT / 4, title);
        }

        public static void show(String title, String message) {
            currentMessage = message;
            new DialogShowResult(title);
            currentMessage = null;
        }

        @Override
        public void addComponents() {
            String message = currentMessage != null ? currentMessage : "";
            Panel panel = new Panel(
                    0, 30, getWidthDialog(), 50,
                    dialog.getBackground(), null,
                    message, 0
            );
            panel.setFont(Config.MONOSPACED_BOLD_16);
            Button button = new Button(
                    getWidthDialog() / 2 - 50, panel.getY() + panel.getHeightPanel() + 30,
                    100, 50, "OK"
            );
            button.addActionListener(e -> dialog.dispose());

            dialog.add(panel);
            dialog.add(button);
        }
    }
    private abstract static class DialogWithFieldText extends Dialog {
        protected TextField textField;
        protected Button button;

        public DialogWithFieldText(
                int x, int y, int width, int height, String title) {
            super(x, y, width, height, title);
        }

        @Override
        public void addComponents() {
            addButtonAndTextField();
            addListeners();
        }

        public void addButtonAndTextField() {
            int numberObjectPerColumn = 2;
            int numberObjectPerRow = 1;
            int buttonWidth = 250;
            int buttonHeight = 50;
            int gapHeight = 20;
            int gapWidth = 40;
            int totalHeight = buttonHeight * numberObjectPerColumn + (numberObjectPerColumn - 1) * gapHeight;
            int totalWidth = buttonWidth * numberObjectPerRow;
            int initialY = (getHeightDialog() - totalHeight) / 2;
            int initialX = (getWidthDialog() - totalWidth) / 2;

            button = new Button(
                    initialX,
                    initialY + (gapHeight + buttonHeight) * (numberObjectPerColumn - 1),
                    buttonWidth, buttonHeight,
                    "Save"
            );

            textField = new TextField(
                    initialX, initialY,
                    buttonWidth, buttonHeight,
                    "0", Color.WHITE, 1, 0, 0
            );

            dialog.add(button);
            dialog.add(textField);
        }

        public abstract void addListeners();
    }
}
