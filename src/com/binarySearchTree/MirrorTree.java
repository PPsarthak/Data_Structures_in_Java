package BST;

public class MirrorTree {
    static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static Node mirror(Node root){
        if(root == null){
            return null;
        }

        Node Left = mirror(root.left);
        Node Right = mirror(root.right);

        root.left = Right;
        root.right = Left;

        return root;
    }


    public static void preorder(Node root){
        if(root == null){
            return;
        }

        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }

    public static void main(String[] args) {
        Node root = new Node(8);
        root.left = new Node(5);
        root.right = new Node(10);
        root.left.left = new Node(3);
        root.left.right = new Node(6);
        root.right.right = new Node(11);

        root = mirror(root);
        preorder(root);
    }
}
