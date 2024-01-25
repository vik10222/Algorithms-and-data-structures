    import java.util.Random;
class LinkedList {
    Node head;
    Node tail;

    static class Node {
        int value;
        Node next;
        Node prev; // Added prev pointer

        Node(int value) {
            this.value = value;
            next = null;
            prev = null; // Initialize prev to null
        }
    }

    public LinkedList() {
        head = null;
        tail = null;
    }

    public void append(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail; // Set the previous pointer of the new node
            tail = newNode;
        }
    }

    
    

    public void quicksort() {
        if (head == tail || head == null || tail == null) return;
    
        Node pivot = head;
        Node current = head.next;
        LinkedList small = new LinkedList();
        LinkedList large = new LinkedList();
    
        while (current != null) {
            Node tmp = current.next;
            if (current.value > pivot.value) {
                large.appendNode(current);
            } else {
                small.appendNode(current);
            }
            current = tmp;
        }
    
        small.quicksort();
        large.quicksort();

        // small.appendNode(pivot);
        // small.appendNode(large.head);
        // head = small.head;
        // tail = small.tail;
    
        if (small.head == null) {
            head = pivot;
            pivot.next = large.head;
            if (large.head != null) large.head.prev = pivot;
            tail = (large.tail != null) ? large.tail : pivot;
        } else {
            small.tail.next = null; // Break the link
            head = small.head;
            small.tail.next = pivot;
            pivot.prev = small.tail;
            pivot.next = large.head;
            if (large.head != null) large.head.prev = pivot;
            tail = (large.tail != null) ? large.tail : pivot;
        }
        
    }
    
    public void appendNode(Node node) {
        if (head == null) {
            head = node;
            tail = node;
            node.next = null;
            node.prev = null;
        } else {
            tail.next = node;
            node.prev = tail;
            node.next = null; // This is the crucial line
            tail = node;
        }
    }
    


public void generateRandomList(int length) {
    Random rand = new Random();
    for (int i = 0; i < length; i++) {
        int randomValue = rand.nextInt(100); // Generates a random integer between 0 and 99
        append(randomValue);
    }
}


   

    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.value + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        for(int power = 6; power< 12 ; power++){
        System.out.println("n: " + Math.pow(2, power));
        list.generateRandomList((int)Math.pow(2, power));

        long sum = 0;
        //System.out.println("Original List:");
        for(int i = 0; i<100000; i++){
        
       // list.printList()
        long t1 = System.nanoTime();
        list.quicksort();
        long t2 = System.nanoTime();
        sum += t2-t1;
        }
        System.out.println(sum/100000);
        //System.out.println("\nSorted List:");
       // list.printList();
    }
    }
}
