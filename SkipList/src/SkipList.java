import java.util.Random;

public class SkipList {
    private static final int MAX_LEVEL = 16;
    private static final double P=0.5; //probability
    private final Node header;
    private int level;
    private final Random random;

    public SkipList() {
        this.header = new Node(Integer.MIN_VALUE, -1, MAX_LEVEL);
        this.random = new Random();
        this.level = 1;
    }

    //helper for generating a final level
    private int randomLevel(){
        int lvl= 1;
        while(random.nextDouble()<P && lvl < MAX_LEVEL){
            lvl++;
        }
        return lvl;
    }

    public int search( int searchKey){
        Node x= this.header;
        for (int i = this.level-1; i>=0; i--){
            while (x.forward[i] != null && x.forward[i].key < searchKey){
                x = x.forward[i];
            }
            if(x.key == searchKey){
                return x.key;
            }
        }
        return -1;
    }

    public void insert(int searchKey, int newKey){
        //update array to store the predecessors
        Node[] update = new Node[MAX_LEVEL];
        Node x= this.header;
        //iterate forward to find the right place to insert
        for(int i = this.level-1; i>=0; i--){
            while (x.forward[i] != null && x.forward[i].key < searchKey){
                x = x.forward[i];
            }

            update[i]=x;
        }

        if( x!= null && x.key == searchKey){
            x.value = newKey;
        }
        else{
            //if new level is bigger than the highest level in the list so far
            int lvl = randomLevel();
            if(lvl > this.level){
                for(int i = this.level; i< lvl; i++){
                    update[i] = this.header;
                }
                this.level=lvl;
            }
            //
            Node newNode= new Node(searchKey, newKey, lvl);
            for(int i = 1; i< lvl; i++){
                newNode.forward[i] = update[i].forward[i]; // new node is pointing at what the predecessor has been poiting at
                update[i].forward[i] = newNode;// standard insertion pointer fix
            }
        }
    }

    public boolean delete(int searchKey){
        Node[] update = new Node[MAX_LEVEL];
        Node x= this.header;
        for(int i = this.level-1; i>=0; i--){
            while (x.forward[i] != null && x.forward[i].key < searchKey){
                x = x.forward[i];
            }
            update[i]=x;
        }
        x=x.forward[0];
        //found the node
        if(x!=null && x.key == searchKey){
            for(int i =0; i<this.level;i++){
                if(update[i].forward[i] != x){
                    break;
                }
                update[i].forward[i] = x.forward[i];
            }
            //if the only only node on the highest level k is deleted, decrease overall level size
            while(this.level>1 && this.header.forward[this.level-1]== null){
                this.level--;
            }
            return true;

        }
        return false;
    }

}







