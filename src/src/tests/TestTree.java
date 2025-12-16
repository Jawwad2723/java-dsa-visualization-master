package src.tests;

public class TestTree implements Test {
    @Override
    public void run() {
        new TestArrayBinaryTree().run();
        new TestLinkedBinaryTree().run();
        new TestBinarySearchTree().run();
    }



}
