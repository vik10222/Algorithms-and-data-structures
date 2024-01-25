import java.util.Random;

public class HeapBenchmark {

    private static final int ELEMENTS = 1023;
    private static final int PUSH_OPERATIONS = 1000;
    private static final Random rand = new Random();

    public static void main(String[] args) {
        Heap heap = new Heap();

        // Add 1023 elements with random values to the heap
        for (int i = 0; i < ELEMENTS; i++) {
            heap.add(rand.nextInt(10001));
        }

        // Run a sequence of push operations
        long startTimePush = System.nanoTime();
        int totalDepthPush = 0;
        for (int i = 0; i < PUSH_OPERATIONS; i++) {
            totalDepthPush += heap.push(rand.nextInt(91) + 10);
        }
        long endTimePush = System.nanoTime();

        // Reset the heap
        heap = new Heap();
        for (int i = 0; i < ELEMENTS; i++) {
            heap.add(rand.nextInt(10001));
        }

        // Run the same benchmark but dequeue the element with the highest priority
long startTimeDequeueAdd = System.nanoTime();
int totalDepthDequeueAdd = 0;
for (int i = 0; i < PUSH_OPERATIONS; i++) {
    int highestPriority = heap.remove();
    totalDepthDequeueAdd += heap.add(highestPriority + rand.nextInt(91) + 10);
}
long endTimeDequeueAdd = System.nanoTime();


        // Print results
        System.out.println("Push method average depth: " + (double) totalDepthPush / PUSH_OPERATIONS);
        System.out.println("Push method execution time: " + (endTimePush - startTimePush) + " ns");
        System.out.println("Dequeue-Add method average depth: " + (double) totalDepthDequeueAdd / PUSH_OPERATIONS);
        System.out.println("Dequeue-Add method execution time: " + (endTimeDequeueAdd - startTimeDequeueAdd) + " ns");

        

                // ArrayHeap heap = new ArrayHeap(10000);
                // long startTime = System.nanoTime();
                // for (int i = 0; i < 1000; i++) {
                //     heap.add((int) (Math.random() * 10000));
                // }
                // for (int i = 0; i < 500; i++) {
                //     heap.poll();
                // }
                // long endTime = System.nanoTime();
                // System.out.println("Array-based heap took: " + (endTime - startTime) + " ns");
        

        
        


    }
}





class Heap {

    class Node {
    int value;
    int size;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
        this.size = 1;
    }
}

    private Node root;

    public int add(int value) {
    int depth = 0;
    if (root == null) {
        root = new Node(value);
    } else {
        Node current = root;
        while (true) {
            depth++;
            current.size++;
            if (value < current.value) {
                int temp = current.value;
                current.value = value;
                value = temp;
            }
            if (current.left == null || (current.right != null && current.left.size > current.right.size)) {
                if (current.right == null) {
                    current.right = new Node(value);
                    break;
                } else {
                    current = current.right;
                }
            } else {
                if (current.left == null) {
                    current.left = new Node(value);
                    break;
                } else {
                    current = current.left;
                }
            }
        }
    }
    return depth;
}


    public int remove() {
    if (root == null) {
        throw new IllegalStateException("Heap is empty");
    }
    int removedValue = root.value;
    if (root.size == 1) {
        root = null;
    } else {
        Node current = root;
        Node parent = null;
        while (current.left != null || current.right != null) {
            current.size--;
            parent = current;
            if (current.right == null || (current.left != null && current.left.value < current.right.value)) {
                current.value = current.left.value;
                current = current.left;
            } else {
                current.value = current.right.value;
                current = current.right;
            }
        }
        if (parent.left == current) {
            parent.left = null;
        } else {
            parent.right = null;
        }
    }
    return removedValue;
}


    public int push(int incr) {
        if (root == null) {
            throw new IllegalStateException("Heap is empty");
        }
        root.value += incr;
        int depth = 0;
        Node current = root;
        while (current.left != null || current.right != null) {
            depth++;
            if (current.right == null || (current.left != null && current.left.value < current.right.value)) {
                if (current.value > current.left.value) {
                    int temp = current.value;
                    current.value = current.left.value;
                    current.left.value = temp;
                    current = current.left;
                } else {
                    break;
                }
            } else {
                if (current.value > current.right.value) {
                    int temp = current.value;
                    current.value = current.right.value;
                    current.right.value = temp;
                    current = current.right;
                } else {
                    break;
                }
            }
        }
        return depth;
    }
}
