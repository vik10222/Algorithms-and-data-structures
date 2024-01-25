public class BinaryTree{
    public class TreeNode{
        public Integer val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(Integer input){
            val = input;
            this.left = null;
            this.right = null;
        }

    }
    public void breadthFirsTraversal{
        if (root == null) return;

        Queue Treequeue = new Queue();

        Treequeue.add(root);
        while (Treequeue.head != null) {
            Node current = Treequeue.remove();
            System.out.println(current.value);
            if (current.left != null) Treequeue.add(current.left);
            if (current.right != null) Treequeue.add(current.right);
        }
    }
}
