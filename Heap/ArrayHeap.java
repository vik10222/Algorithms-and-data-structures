import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayHeap {
    private int[] heap;
    private int size;

    public ArrayHeap(int capacity) {
        heap = new int[capacity];
        size = 0;
    }

    // Helper methods to get parent and child indices
    private int getParentIndex(int index) { return (index - 1) / 2; }
    private int getLeftChildIndex(int index) { return 2 * index + 1; }
    private int getRightChildIndex(int index) { return 2 * index + 2; }

    // Helper methods to check existence of parent or child
    private boolean hasParent(int index) { return getParentIndex(index) >= 0; }
    private boolean hasLeftChild(int index) { return getLeftChildIndex(index) < size; }
    private boolean hasRightChild(int index) { return getRightChildIndex(index) < size; }

    // Helper methods to get values of parent or child
    private int parent(int index) { return heap[getParentIndex(index)]; }
    private int leftChild(int index) { return heap[getLeftChildIndex(index)]; }
    private int rightChild(int index) { return heap[getRightChildIndex(index)]; }

    // Swap values in the heap
    private void swap(int indexOne, int indexTwo) {
        int temp = heap[indexOne];
        heap[indexOne] = heap[indexTwo];
        heap[indexTwo] = temp;
    }

    // Ensure the heap has capacity to add elements
    private void ensureCapacity() {
        if (size == heap.length) {
            heap = Arrays.copyOf(heap, heap.length * 2);
        }
    }

    public int peek() {
        if (size == 0) throw new IllegalStateException();
        return heap[0];
    }

    public int poll() {
        if (size == 0) throw new IllegalStateException();
        int item = heap[0];
        heap[0] = heap[size - 1];
        size--;
        sinkDown();
        return item;
    }

    public void add(int item) {
        ensureCapacity();
        heap[size] = item;
        size++;
        bubbleUp();
    }

    private void bubbleUp() {
        int index = size - 1;
        while (hasParent(index) && parent(index) > heap[index]) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    private void sinkDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && rightChild(index) < leftChild(index)) {
                smallerChildIndex = getRightChildIndex(index);
            }

            if (heap[index] < heap[smallerChildIndex]) {
                break;
            } else {
                swap(index, smallerChildIndex);
            }
            index = smallerChildIndex;
        }
    }



    public static void main(String[] args) {
        ArrayHeap heap = new ArrayHeap(10000);
        long startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            heap.add((int) (Math.random() * 10000));
        }
        for (int i = 0; i < 500; i++) {
            heap.poll();
        }
        long endTime = System.nanoTime();
        System.out.println("Array-based heap took: " + (endTime - startTime) + " ns");

        // Repeat the same for the linked structure heap and compare.
    }


}
