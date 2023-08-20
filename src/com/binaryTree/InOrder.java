package com.binaryTree;

public class InOrder {

    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

        static class BinaryTrees{
            static int index = -1;
            public static Node buildTrees(int nodes[]){
                index++;
                if(nodes[index] == -1){
                    return null;
                }

                Node NewNode = new Node(nodes[index]);
                NewNode.left = buildTrees(nodes);
                NewNode.right = buildTrees(nodes);

                return NewNode;
            }
            public static void InOrder(Node root){
                if(root == null) return;
                InOrder(root.left);
                System.out.print(root.data + " ");
                InOrder(root.right);
            }
        }
    public static void main(String[] args) {
        int[] nodes = {1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};

        BinaryTrees T = new BinaryTrees();
        Node root = T.buildTrees(nodes);
        System.out.println(root.data);

        T.InOrder(root);
    }
}
