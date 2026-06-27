public class Node {
    int key;
    int value;
    Node[] forward;

    public Node(int key, int value, int level) {
        this.key = key;
        this.value = value;
        this.forward = new Node[level]; //pointers to the next levels
    }

}
