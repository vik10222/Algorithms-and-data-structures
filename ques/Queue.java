public class Queue{
    Node head;
    Node tail;
    public class Node {
        public Integer item;
        public Node tail;
        public Node(Integer item, Node list) {
                this.item = item;
                this.tail = list;
        }
    }
    public Queue() {
        head = null;
        tail = null;

    }
    public void add(Integer item) {
        Node newNode = new Node(item, null);
        while (tail != null){
            tail = tail.next;
        }if (tail == null){
            tail =newNode;

        }
    }
    public Integer remove() {
        while (head != null){
            Node RNode = head;
            head = head.next;
        } if (head == null){
            head = null;
            tail = null;
            return RNode.item;
        }

            

        }

    
    public static void main(String[] args) {
        Queue Q1 = new Queue();
        Queue.add(12);
        Queue.add(13);
    }

}