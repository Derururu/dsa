import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BSTInsertionTests {

    public static void main(String[] args) {
        testSingleInsertBecomesRoot();
        testInsertLeftAndRightChildren();
        testInsertDeeperTreeKeepsParentsCorrect();
        testDuplicateKeysGoToTheRight();
        testInorderTraversalIsSortedAfterInsertion();

        System.out.println("All BST insertion tests passed.");
    }

    private static void testSingleInsertBecomesRoot() {
        BST bst = new BST();
        Node node = new Node(10);

        bst.insert(node);

        expectSame(node, bst.getRoot(), "root should be the first inserted node");
        expectNull(node.getParent(), "root parent should be null");
    }

    private static void testInsertLeftAndRightChildren() {
        BST bst = new BST();
        Node root = new Node(10);
        Node left = new Node(5);
        Node right = new Node(15);

        bst.insert(root);
        bst.insert(left);
        bst.insert(right);

        expectSame(left, root.getLeft(), "left child should be attached to the root");
        expectSame(right, root.getRight(), "right child should be attached to the root");
        expectSame(root, left.getParent(), "left child parent should point to root");
        expectSame(root, right.getParent(), "right child parent should point to root");
    }

    private static void testInsertDeeperTreeKeepsParentsCorrect() {
        BST bst = new BST();
        Node root = new Node(10);
        Node n5 = new Node(5);
        Node n15 = new Node(15);
        Node n2 = new Node(2);
        Node n7 = new Node(7);
        Node n12 = new Node(12);
        Node n20 = new Node(20);

        bst.insert(root);
        bst.insert(n5);
        bst.insert(n15);
        bst.insert(n2);
        bst.insert(n7);
        bst.insert(n12);
        bst.insert(n20);

        expectSame(n5, root.getLeft(), "5 should be left of 10");
        expectSame(n15, root.getRight(), "15 should be right of 10");
        expectSame(n2, n5.getLeft(), "2 should be left of 5");
        expectSame(n7, n5.getRight(), "7 should be right of 5");
        expectSame(n12, n15.getLeft(), "12 should be left of 15");
        expectSame(n20, n15.getRight(), "20 should be right of 15");

        expectSame(root, n5.getParent(), "5 parent should be 10");
        expectSame(root, n15.getParent(), "15 parent should be 10");
        expectSame(n5, n2.getParent(), "2 parent should be 5");
        expectSame(n5, n7.getParent(), "7 parent should be 5");
        expectSame(n15, n12.getParent(), "12 parent should be 15");
        expectSame(n15, n20.getParent(), "20 parent should be 15");
    }

    private static void testDuplicateKeysGoToTheRight() {
        BST bst = new BST();
        Node root = new Node(10);
        Node duplicate = new Node(10);

        bst.insert(root);
        bst.insert(duplicate);

        expectSame(duplicate, root.getRight(), "duplicate key should be inserted on the right in this implementation");
        expectSame(root, duplicate.getParent(), "duplicate node parent should be root");
    }

    private static void testInorderTraversalIsSortedAfterInsertion() {
        BST bst = new BST();
        int[] values = {10, 5, 15, 2, 7, 12, 20};

        for (int value : values) {
            bst.insert(new Node(value));
        }

        String traversal = captureInorderTraversal(bst);
        expectEquals("2 5 7 10 12 15 20 ", traversal, "inorder traversal should print sorted values");
    }

    private static String captureInorderTraversal(BST bst) {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream capture = new PrintStream(buffer);

        try {
            System.setOut(capture);
            bst.InorderTraversal(bst.getRoot());
        } finally {
            System.setOut(originalOut);
            capture.close();
        }

        return buffer.toString();
    }

    private static void expectSame(Object expected, Object actual, String message) {
        if (expected != actual) {
            throw new AssertionError(message + " (expected same reference)");
        }
    }

    private static void expectNull(Object actual, String message) {
        if (actual != null) {
            throw new AssertionError(message + " (expected null)");
        }
    }

    private static void expectEquals(String expected, String actual, String message) {
        if (!expected.equals(actual)) {
            throw new AssertionError(message + " (expected '" + expected + "', got '" + actual + "')");
        }
    }
}
