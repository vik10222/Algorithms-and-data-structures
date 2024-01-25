public class ArrayQueue{
    private Integer[] array;
    private int head;
    private int tail;
    private int size;

    public ArrayQueue(int length){
    array = new Integer[length];
        head = 0;
        tail = 0;
        size  = 0;

    }
    
    
    public Integer remove() {
        if (size == 0) return null;
        Integer item = array[head];
        array[head] = null;
        head++;
        if (head == array.length) head = 0;
        size--;

        if (size > 0 && size == array.length / 4) {
            resize(array.length / 2);
        }
        return item;
    }

    private void resize(int capacity) {
        Integer[] copy = new Integer[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = array[(head + i) % array.length];
        }
        array = copy;
        head = 0;
        tail = size;
    }
}




