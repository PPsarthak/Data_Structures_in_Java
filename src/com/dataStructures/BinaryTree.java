package com.dataStructures;

import java.util.*;

class BinaryTree {
    private static class Node{
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
    static int index = -1;
    public static Node buildTree(int[] nodes){
        index++;
        if(nodes[index]==-1) return null;
        Node root;
        root = new Node(nodes[index]);
        root.left = buildTree(nodes);
        root.right = buildTree(nodes);
        return root;
    }
    //Time Complexity for any traversal = O(n)
    public static void preOrder(Node root){
        if(root == null) return;
        System.out.print(root.value + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void inOrder(Node root){
        if(root == null) return;
        inOrder(root.left);
        System.out.print(root.value + " ");
        inOrder(root.right);
    }

    public static void postOrder(Node root){
        if(root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.value + " ");
    }
    //better approach for BFS traversal
    public static List<List<Integer>> levelOrder2(Node root){
        Queue<Node> q = new LinkedList<>();
        List<List<Integer>> levelOrderList = new ArrayList<>();
        if(root == null) return levelOrderList;
        q.offer(root);
        while (!q.isEmpty()){
            int level = q.size();
            List<Integer> myList = new ArrayList<>();
            for (int i = 0; i < level; i++) {
                if(q.peek().left!=null){
                    q.offer(q.peek().left);
                }
                if(q.peek().right!=null){
                    q.offer(q.peek().right);
                }
                myList.add(q.poll().value);
            }
            levelOrderList.add(myList);
        }
        return levelOrderList;
    }
    public static void levelOrder(Node root){
        Queue<Node> myQ = new LinkedList<>();
        if(root == null) return;
        myQ.add(root);
        myQ.add(null);
        while (!myQ.isEmpty()){
            Node curr = myQ.poll();
            if(curr == null){
                System.out.println(" ");
                if (myQ.isEmpty()) break;
                myQ.add(null);
            }
            else{
                System.out.print(curr.value);
                if (curr.left!=null) myQ.add(curr.left);
                if (curr.right!=null) myQ.add(curr.right);
            }
        }
    }

    public static int countNodes(Node root){
        if (root == null){
            return 0;
        }
        int leftNodes = countNodes(root.left);
        int rightNodes = countNodes(root.right);
        return leftNodes + rightNodes + 1;
    }

    public static int sumNodes(Node root){
        if (root == null){
            return 0;
        }
        int leftSum = sumNodes(root.left);
        int rightSum = sumNodes(root.right);
        return leftSum + rightSum + root.value;
    }

    public static int getHeight(Node root){
        if (root == null){
            return 0;
        }
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        return Math.max(leftHeight,rightHeight)+1;
    }

    public static boolean subTree(Node root, Node subRoot){
        if(subRoot == null) return true;
        if(root == null) return false;
        if(root.value == subRoot.value){
            return isIdentical(root, subRoot);
        }
        return subTree(root.left, subRoot) || subTree(root.right, subRoot);
    }

    private static boolean isIdentical(Node root, Node subRoot){
        if (root == null && subRoot == null) return true;
        if (root == null || subRoot == null) return false;
        if (root.value == subRoot.value){
            return isIdentical(root.left, subRoot.left) && isIdentical(root.right, subRoot.right);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] myArr = {1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
        Node root = buildTree(myArr);
        levelOrder(root);
        System.out.println(levelOrder2(root));
//        System.out.println(sumNodes(root));
//        System.out.println(getHeight(root));
    }
}


