import java.util.Random;

public class DoubleLinkedList {
    private Node head;
    private Node tail;

    public DoubleLinkedList() {
        this.head = null;
        this.tail = null;  // Initialize tail to null
    }

    void addFirstonly(int data) {
        if (this.head != null) {
            System.out.println("already got a head mate");
        } else {
            Node newNode = new Node(null, data, null);
            this.head = newNode;
            this.tail = newNode;
        }
    }

    void addnormal(int data) {
        Node newNode = new Node(this.tail, data, null);
        this.tail.next = newNode;  // Update the next pointer of the current tail
        this.tail = newNode;       // Set the new node as the new tail
    }
    

    public void insertNode(Node node) {
        // if  list is empty
        if (this.head == null) {
            this.head = node;
            this.tail = node;
            node.previous = null;
            node.next = null;
        } else {
            // If the list is not empty
            node.next = this.head;
            this.head.previous = node;
            node.previous = null;
            this.head = node;
        }
    }


    int getLength() {
        int length = 0;
        Node counterNode = this.head;
        while (counterNode != null) {  
            counterNode = counterNode.next;  
            length++;
        }
        return length;
    }

    boolean find(int data){
        Node iteratorNode = this.head;
        while (iteratorNode != null){
            if(iteratorNode.data == data){
                return true;
            }
            iteratorNode = iteratorNode.next;
        }
            return false; 
    }
    
    void removeVAL(int val) {
        if(find(val)==false){System.out.println("shit does not exist mate");}
        Node removerNode = this.head;
        while (removerNode != null) {
            if (removerNode.data == val) {
                // If node to be removed is the head
                if (removerNode == this.head) {
                    this.head = removerNode.next;
                    if (this.head != null) {
                        this.head.previous = null;
                    }
                    // If node to be removed is the tail
                } else if(removerNode == this.tail) {
                    this.tail = removerNode.previous;}
                    else{
                    removerNode.previous.next = removerNode.next;
                    if (removerNode.next != null) {
                        removerNode.next.previous = removerNode.previous;
                    }
                }
                
            }
            removerNode = removerNode.next;
        }
    }


    void removeNode(Node node){
        if( node ==this.head){this.head = node.next;}
        if(node == this.tail){this.tail =node.previous;}
        node.previous =node.next;
        node.next = node.previous;
    }
    




    public void printAll() {
        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }


    public Node getRandomNode() {
        int length = this.getLength();
        if (length == 0) {
            throw new RuntimeException("The list is empty");
        }
        Node[] nodeArray = new Node[length];

        Node currentNode = this.head;
        for (int i = 0; i < length; i++) {
            nodeArray[i] = currentNode;
            currentNode = currentNode.next;
        }

        Random rand = new Random();
        int randomIndex = rand.nextInt(length);
        return nodeArray[randomIndex];
    }
    

    public static int getRandomNumber(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
    
    

    private static class Node {
        Node previous;
        Node next;
        int data;

        Node(Node previous, int data, Node next) {
            this.previous = previous;
            this.next = next;
            this.data = data;
        }
    }


     void remove(Node node) {
        if (node == this.head) {  
            if (node.next == null) {
            this.head = null;
            }
            else {
            this.head = node.next;
            this.head.previous = null;
            }
        }
        else {
        if (node.next == null) {
        node.previous.next = null;
        }
        else {
        node.previous.next = node.next;
        node.next.previous = node.previous;
        }
        }
        }
     

    public static void main(String[] args) {
    int val = 1000000;
    for( int z=4; z <12; z++){ int N = (int)Math.pow(2,z);
                    DoubleLinkedList doubleList = new DoubleLinkedList();
            for(int i =0; i< N; i++){
                if(i==0){doubleList.addFirstonly(i);}
                else{doubleList.addnormal(i);}
            }
         

        long sum =0;
        for( int k = 0; k<val; k++){    //these two lines set up our lovely benching for set sizesfun times
            Node x = doubleList.getRandomNode();
            long t1 = System.nanoTime();
            doubleList.remove(x);
            doubleList.insertNode(x);
            long t2 =System.nanoTime();
            
            sum += t2-t1;
        }
        System.out.println("DLL     Size " + N + " Time: " + sum/val);




    }


}

}
