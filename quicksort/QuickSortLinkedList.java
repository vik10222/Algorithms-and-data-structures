public class QuickSortLinkedList {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.append(9);
        list.append(7);
        list.append(5);
        list.append(11);
        list.append(12);
        list.append(2);
        list.append(14);
        list.append(3);
        list.append(10);
        list.append(6);

        System.out.println("Original List:");
        list.printList();

        list.sort();

        System.out.println("\nSorted List:");
        list.printList();
    }
}
