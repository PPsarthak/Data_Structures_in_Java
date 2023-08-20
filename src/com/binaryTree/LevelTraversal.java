package com.binaryTree;
import java.util.LinkedList;
import java.util.Queue;

public class LevelTraversal {
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
    static class BiinaryTree{
        static int index = -1;
        public static Node buildTree(int[] nodes){
            index++;
            if(nodes[index] == -1){
                return null;
            }
            Node newNode = new Node(nodes[index]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);
            return newNode;
        }
        public static void levelTra(Node root){
            if(root == null){
                return;
            }
            Queue<Node> Q = new LinkedList<>();
            Q.add(root);
            Q.add(null);

            while(!Q.isEmpty()){
                Node curr = Q.remove();
                if(curr == null){
                    System.out.println();
                    if(Q.isEmpty()){
                        break;
                    }else{
                        Q.add(null);
                    }
                }else{
                    System.out.print(curr.data + " ");
                    if(curr.left != null){
                        Q.add(curr.left);
                    }
                    if(curr.right != null){
                        Q.add(curr.right);
                    }
                }

            }
        }
    }
    public static void main(String[] args) {
        int[] nodes = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};
        BiinaryTree BT = new BiinaryTree();
        Node root = BT.buildTree(nodes);
        System.out.println(root.data);
        BT.levelTra(root);
    }
}
