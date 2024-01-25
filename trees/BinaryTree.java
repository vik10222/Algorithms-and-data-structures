import java.util.Random;
import java.util.Iterator;
import java.util.Stack;


public class BinaryTree implements Iterable<Integer> {

    Node root;
    public BinaryTree() {
        root = null;
        }



    public class Node {
        public Integer key;
        public Integer value;
        public Node left, right;

        public Node(Integer key, Integer value) {
                this.key = key;
                this.value = value;
                this.left = this.right = null;
         }
         //chai's inner node stuff
         private void add (Integer key, Integer value) {
            if (this.key == key) {
            this.value = value;
            }
            else if (this.key > key) {
            if (this.left != null)
            this.left.add(key, value);
            else
            this.left = new Node(key,value);
            }
            else {
            if (this.right != null)
            this.right.add(key, value);
            else
            this.right = new Node(key, value);

            }
            }

        public void print() {
            if(left != null)
            left.print();
            System.out.println(" key: " + key + "\tvalue: " + value);
            if(right != null)
            right.print();
        }



        }

    // public void add(Integer key, Integer val) {
    //     Node newNode = new Node(key, val);
    //     if (root == null) {
    //         root = newNode;
    //         return;
    //     }

    //     Node currentNode = root;
    //     Node parent = null;

    //     while (currentNode != null) {
    //         parent = currentNode;
    //         if (key < currentNode.key) {
    //             currentNode = currentNode.left;
    //         } else if (key > currentNode.key) {
    //             currentNode = currentNode.right;
    //         } else {
    //             // Key already exists, update the value and return
    //             currentNode.value = val;
    //             return;
    //         }
    //     }

    //     if (key < parent.key) {
    //         parent.left = newNode;
    //     } else {
    //         parent.right = newNode;
    //     }
    // }


        

//chai's
public void add(Integer key, Integer value) {
    if (this.root == null) {
    this.root = new Node(key, value);
    } else {
    root.add(key, value);
    }
    }

//     public Integer lookup(Integer key){
//         if( root ==null){
//             System.out.println("empty tree");
//         return null;}
//         Node currentNode = root;
//         while (currentNode != null) {
//             if(currentNode.key ==key){return currentNode.value;}
//             else if (key < currentNode.key) {
//                 currentNode = currentNode.left;
//             } else if (key > currentNode.key) {
//                 currentNode = currentNode.right;
//             }
//     }
//     //System.out.println("no such key");
//     return null; 
// }


//chai's
public Integer lookup(Integer key) {
    Node current_node = this.root;
    while(current_node != null) {
    if(current_node.key == key)
    return current_node.value;
    else if(current_node.key < key)
    current_node = current_node.right;
    else
    current_node = current_node.left;
    }
    return null;
    }

    public void printTree() {
        if (root != null) {
            root.print();
        } else {
            System.out.println("The tree is empty.");
        }
    }
    

    @Override
    public Iterator<Integer> iterator() {
        return new TreeIterator();
    }

    public class TreeIterator implements Iterator<Integer> {
        private Node next;
        private Stack<Node> stack = new Stack<>();

        public TreeIterator() {
            next = root;
            while (next != null) {
                stack.push(next);
                next = next.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Integer next() {
            Node curr = stack.pop();
            Integer value = curr.value;
            if (curr.right != null) {
                curr = curr.right;
                while (curr != null) {
                    stack.push(curr);
                    curr = curr.left;
                }
            }
            return value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    

// public static void main(String[] args) {
//     BinaryTree bst = new BinaryTree();
//     Random rand = new Random();
//     int reps = 1000000;
//     for(int k=1; k<8 ;k++){
//         for (int i = 0; i < Math.pow(10, k); i++) {
//             Integer randomKey = rand.nextInt(1000);  // Generate random keys between 0 and 999
//             Integer randomValue = rand.nextInt(1000);  // Generate random values between 0 and 999
//             bst.add(randomKey, randomValue);

//         } 
        
//         long sum = 0;
//         for(int i = 0; i<reps; i++){
//             Integer lookupkey = rand.nextInt(1000);
//             long t1 = System.nanoTime();
//             Integer x = bst.lookup(lookupkey);
//             long t2 = System.nanoTime();
//             sum += t2-t1;
//             // System.out.println("l2 "+ i);
//         }
//         System.out.println("size: "+ Math.pow(10, k)+ " Time: " + sum/reps);
//     }
//     System.out.println("done");
// }
public static void main(String[] args) {
    

BinaryTree tree = new BinaryTree();
tree.add(5,105);
tree.add(2,102);
tree.add(7,107);
tree.add(1,101);
tree.add(8,108);
tree.add(6,106);
tree.add(3,103);
for (int i : tree)
System.out.println("next value " + i);

}
}