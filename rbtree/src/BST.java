
public class BST {
    private Node root;
    public BST() {
        root = null;
    }
    public Node getRoot() {
        return root;
    }
    public Node getMax(Node x){ // returns the max in the subtree
        Node current = x;
        while(current != null){
            current = current.getRight();
        }
        return current;
    }
    public Node getMin(Node x){
        Node current = x;
        while(current != null){
            current = current.getLeft();
        }
        return current;
    }
    public void InorderTraversal(Node root){
        if(root != null){
            InorderTraversal(root.getLeft());
            System.out.print(root.getValue() + " ");
            InorderTraversal(root.getRight());
        }
    }


    public Node BSTsearch(Node x, int key){
        if(x == null|| x.getValue() == key){
            return x;
        }
        else if(key < x.getValue()){
            return BSTsearch(x.getLeft(), key);
        }
        else if(key > x.getValue()){
            return BSTsearch(x.getRight(), key);
        }
        return null;
    }




    public Node BSTsuccessor(Node x){
        if(x.getRight() == null){
            return getMin(x.getRight());
        }
        Node y = x.getParent();
        while (y!= null && x== y.getRight()){
            x = y;
            y = y.getParent();
        }
        return y;
    }

    public void insert(Node z){
        Node y= null;
        Node x = root;
        while(x!=null){
            y  = x;
            if(z.getValue() < x.getValue()){
                x= x.getLeft();
            }
            else {
                x= x.getRight();
            }
        }
        z.setParent(y);
        if(y == null){
            root = z;
        }
        else{
            if (z.getValue() < y.getValue()) {y.setLeft(z);} else {y.setRight(z);}
        }
    }


    public Node BSTpredecessor(Node x){
        if(x.getLeft() == null){
            return getMax(x.getRight()); // same as successor but just max in the sequence before. also left subtree
        }
        Node y = x.getParent();
        while (y!= null && x== y.getLeft()){
            x = y;
            y = y.getParent();
        }
        return y;
    }

    public void delete(Node z){
        /*deletions has 3 cases:
        * Case 1: leaf: pull everything up
        * case 2: has one child: replace deleted with child
        * Case 3: has two children, replace with inorder successor
        * */

    }
}
