import java.util.Random;

public class LinkedList {
    private Node head;  // The starting node of the list

    // Constructor to create an empty list
    public LinkedList() {
        this.head = null;
    }

    // Method to add a node to the end of the list
    public void appendData(int data) {
        Node newNode = new Node(data, null);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void appendList(LinkedList appendList) {
        if (this.head == null) {
            this.head = appendList.head;
        } else {
            Node current = this.head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = appendList.head;
        }
    }

    public void printAll() {
        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    private static class Node {
        int data;
        Node next;

        Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public LinkedList deepCopy() {
        LinkedList copiedList = new LinkedList();
        Node current = this.head;
        while (current != null) {
            copiedList.appendData(current.data);
            current = current.next;
        }
        return copiedList;
    }

    public int pop() {
        if (head == null) {
            throw new RuntimeException("List is empty");
        }
        if (head.next == null) {
            int value = head.data;
            head = null;
            return value;
        }
        Node current = head;
        while (current.next.next != null) {
            current = current.next;
        }
        int value = current.next.data;
        current.next = null;
        return value;
    }

    void removeval(int val) {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        if (head.data == val) {
            head = head.next;
            return;
        }

        Node current = head;
        Node previous = null;

        while (current != null && current.data != val) {
            previous = current;
            current = current.next;
        }

        if (current == null) {
            System.out.println("Value does not exist");
            return;
        }

        previous.next = current.next;
    }

    void addFirstonly(int data) {
        if (this.head != null) {
            System.out.println("List already has a head");
        } else {
            Node newNode = new Node(data, null);
            this.head = newNode;
        }
    }

    void addnormal(int data) {
        Node newNode = new Node(data, null);
        if (this.head == null) {
            this.head = newNode;
        } else {
            Node current = this.head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public static int getRandomNumber(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    public void remove(Node b) {
        if (head == null) {
            return;
        }

        if (b == this.head) {
            this.head = b.next;
            return;
        }

        Node previous = null;
        Node current = this.head;
        while (current != null && current != b) {
            previous = current;
            current = current.next;
        }

        if (current == null) {
            return;
        }

        previous.next = current.next;
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

    public int getLength() {
        int length = 0;
        Node current = this.head;
        while (current != null) {
            length++;
            current = current.next;
        }
        return length;
    }

    public static void main(String[] args) {
        int val = 10000000;
        for (int z = 4; z < 12; z++) {
            int N = (int) Math.pow(2, z);
            LinkedList list = new LinkedList();
            for (int i = 0; i < N; i++) {
                if (i == 0) {
                    list.addFirstonly(i);
                } else {
                    list.addnormal(i);
                }
            }

            long sum = 0;
            for (int k = 0; k < val; k++) {
                Node x = list.getRandomNode();
                long t1 = System.nanoTime();
                list.remove(x);
                list.addnormal(x.data);
                long t2 = System.nanoTime();
                   
                sum += t2 - t1;
            }
            System.out.println("SLL:   Size " + N + " Time: " + sum / val);
        }
    }
}


    // Benching the value of when appended list increases
    // public static void main(String[] args) {
    //     LinkedList listA = new LinkedList();
    //     listA.appendData(11);
    //     listA.appendData(12);
    //     listA.appendData(13);
    //     listA.appendData(14);
    //     listA.appendData(15);

    //     LinkedList listB = new LinkedList();
    //     for(int k=8; k<14;k++){

    //     for(int i=0; i<Math.pow(2,k);i++){
    //         listB.appendData(i);
    //     }

    
    //     int sum = 0;
        
    //     for(int i=0; i<1000000; i++){
    //         LinkedList listACopy = listA.deepCopy(); 
    //         long t1 = System.nanoTime();
    //         listACopy.appendList(listB);
    //         long t2 = System.nanoTime();
    //         sum += t2-t1;

    //     }
   

    //            System.out.println( "Size N "+ Math.pow(2,k) + " the benchmark time is "+ sum/1000000);
    // }
    // }

    //Benching value for when the list to which we append increases:
    // public static void main(String[] args) {
    //     LinkedList listA = new LinkedList();

    //     for (int z = 8; z < 14; z++) {
    //         for (int i = 0; i < Math.pow(2, z); i++) {
    //             listA.appendData(i);
    //         }

    //         LinkedList listB = new LinkedList();
    //         Node node5OfB = new Node(25, null);
    //         Node node4OfB = new Node(24, node5OfB);
    //         Node node3OfB = new Node(23, node4OfB);
    //         Node node2OfB = new Node(22, node3OfB);
    //         Node node1OfB = new Node(21, node2OfB);

    //         listB.head = node1OfB; // Set the head of listB to the first node

    //         int sum = 0;
    //         for (int i = 0; i < 100000; i++) {
    //             LinkedList listACopy = listA.deepCopy();
    //             long t1 = System.nanoTime();
    //             listACopy.appendList(listB);
    //             long t2 = System.nanoTime();
    //             sum += t2 - t1;
    //         }

    //         System.out.println("Size N " + Math.pow(2, z) + " the benchmark time is " + sum / 100000);
            
    //     }
    // }
    //Benching with array
// public static void main(String[] args) {
//     int[] array1 = new int[0]; // Initialize with default size
//     int[] array2 = new int[10];
    
//     for (int z = 8; z < 14; z++) {
//         array1 = new int[(int) Math.pow(2, z)]; 
//         for (int i = 0; i < array1.length; i++) {
//             array1[i] = i;
//         }
        
//         for (int m = 0; m < array2.length; m++) {
//             array2[m] = m;
//         }
    
    
//     int sum = 0;
//     for (int j = 0; j < 100000; j++) {
//         long t1 = System.nanoTime();
//         int[] array3 = new int[array2.length + array1.length];  
        
//         for (int k = 0; k < array1.length; k++) {
//             array3[k] = array1[k];
//         }
        
//         for (int k = 0; k < array2.length; k++) {
//             array3[array1.length + k] = array2[k];
//         }
        
//         long t2 = System.nanoTime();
//         sum +=t2-t1;
//     }
//     System.out.println("size "+ Math.pow(2, z)+ "Bench " + sum/100000);
// }
// }


// public static void main(String[] args) {
//     // Benchmarking Linked List Allocation
//     int val = 10000;
//     for(int z =8; z<14; z++){
//     int N = (int)Math.pow(2, z);  
//     LinkedList a = new LinkedList();  // Corrected this line

//     long sum1=0;
//     for(int h=0; h<val; h++){
//     long startTimel = System.nanoTime();
//     for (int j = 0; j < N - 1; j++) {
//         LinkedList k = new LinkedList();  // Corrected this line
//         k.appendList(a);  // Corrected this line
//         a = k;
//     }
//     long endTimel = System.nanoTime();
//     sum1 += endTimel - startTimel;
//     }




//     // Benchmarking Array Allocation
//     int[] array1 = new int[N];
//     int[] array2 = new int[10];
//     long sum2 =0;
//     for(int h=0; h<val; h++){
//     long startTimea = System.nanoTime();
//     int[] array3 = new int[array2.length + array1.length];
//     for (int i = 0; i < N; i++) {
//         array3[i] = array1[i];
//     }
//     for (int i = 0; i < array2.length; i++) {
//         array3[array1.length + i] = array2[i];
//     }
//     long endTimea = System.nanoTime();
//     sum1 += endTimea - startTimea;
// }
//     System.out.println(N +": linklist time "+ sum1/val + " Array Allocation Time: " + sum2/val + " nanoseconds");
// }
// }









