package com.dataStructures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class BinarySearchTree {
    public Node root;

    public BinarySearchTree() {
        root = null;
    }
    public void insert(int val) {
        root = insert(root, val);
    }
    public Node insert(Node root, int value){
        if(root == null){
            return new Node(value);
        }

        if(root.value > value){
            root.left = insert(root.left,value);
        }
        else if(root.value < value){
            root.right = insert(root.right, value);
        }
        return root;
    }

    Node delete(Node root, int value){
        //1. Search the Node
        if(root.value > value){
            root.left = delete(root.left, value);
        }
        else if(root.value < value){
            root.right = delete(root.right, value);
        }
        else{
            //1. No child
            if(root.left == null && root.right == null){
                return null;
            }
            //2. 1 child
            else if (root.left == null || root.right == null) {
                if(root.right!=null){
                    return root.right;
                }
                else{
                    return root.left;
                }
            }
            else{
                //inorder successor is always the leftmost node in the right subtree of the node to be deleted
                Node ins = inOrderSuccessor(root.right);
                root.value = ins.value;
                root.right = delete(root.right, ins.value);
            }
        }
        return root;
    }
    private Node inOrderSuccessor(Node root) {
        while (root.left!=null){
            root = root.left;
        }
        return root;
    }


    void printInRange(Node root, int x, int y){
        if(root == null) return;
        if(root.value > y){
            printInRange(root.left, x, y);
        }
        else if(root.value < x){
            printInRange(root.right, x, y);
        }
        else{
            printInRange(root.left, x, y);
            System.out.println(root.value);
            printInRange(root.right, x, y);
        }
    }

    void printRoot2Leaf(Node root, ArrayList<Integer> myList){
        if(root == null){
            return;
        }
        myList.add(root.value);
        if(root.left == null && root.right == null){
            for (int i = 0; i < myList.size(); i++) {
                System.out.print(myList.get(i) + "->");
            }
            System.out.println();
        }
        else{
            printRoot2Leaf(root.left, myList);
            printRoot2Leaf(root.right, myList);
        }
        myList.remove(myList.size()-1);
    }

    //TC = O(H), where H = Height of BST
    boolean search(Node root, int value){
        if(root == null){
            return false;
        }
        if(root.value == value) return true;
        else if(root.value > value) return search(root.left, value);
        else return search(root.right, value);
    }
    public void preOrder(Node root){
        if(root == null) return;
        System.out.print(root.value + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public void inOrder(Node root){
        if(root == null) return;
        inOrder(root.left);
        System.out.print(root.value + " ");
        inOrder(root.right);
    }

    public void postOrder(Node root){
        if(root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.value + " ");
    }

    public void levelOrder(Node root){
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

    public int countNodes(Node root){
        if (root == null){
            return 0;
        }
        int leftNodes = countNodes(root.left);
        int rightNodes = countNodes(root.right);
        return leftNodes + rightNodes + 1;
    }

    public int sumNodes(Node root){
        if (root == null){
            return 0;
        }
        int leftSum = sumNodes(root.left);
        int rightSum = sumNodes(root.right);
        return leftSum + rightSum + root.value;
    }

    public int getHeight(Node root){
        if (root == null){
            return 0;
        }
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        return Math.max(leftHeight,rightHeight)+1;
    }

    public boolean subTree(Node root, Node subRoot){
        if(subRoot == null) return true;
        if(root == null) return false;
        if(root.value == subRoot.value){
            return isIdentical(root, subRoot);
        }
        return subTree(root.left, subRoot) || subTree(root.right, subRoot);
    }

    private boolean isIdentical(Node root, Node subRoot){
        if (root == null && subRoot == null) return true;
        if (root == null || subRoot == null) return false;
        if (root.value == subRoot.value){
            return isIdentical(root.left, subRoot.left) && isIdentical(root.right, subRoot.right);
        }
        return false;
    }
    private static class Node{
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }
}


