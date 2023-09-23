package com.dataStructures;

public class AVLTrees {
    static class Node {
        int value, height;
        Node left, right;

        Node(int value) {
            this.value = value;
            height = 1;
        }

        public int getHeight() {
            return height;
        }
    }
    Node root;

    Node insert(Node root, int value){
        /* normal insertion */
        if (value < root.value)
            root.left = insert(root.left, value);
        else if (value > root.value)
            root.right = insert(root.right, value);
        else // Duplicate values not allowed
            return root;

        /* After insertion, update the height of the parent */
        root.height = 1 + Math.max(root.left.height, root.right.height);

        /* Check the height balance (Left - Right)
        * of the node where it is inserted */
        int balance = getBalance(root);

        /* Find the appropriate case and re-balance the tree */
        //Left-Left Case
        if (balance > 1 && value < root.left.value)
            return rightRotate(root);

        // Right-Right Case
        if (balance < -1 && value > root.right.value)
            return leftRotate(root);

        // Left-Right Case
        if (balance > 1 && value > root.left.value) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Left Case
        if (balance < -1 && value < root.right.value) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }
    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        //  Update heights
        x.height = Math.max(x.left.height, x.right.height) + 1;
        y.height = Math.max(y.left.height, y.right.height) + 1;

        return y;
    }
    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(y.left.height, y.right.height) + 1;
        x.height = Math.max(x.left.height, x.right.height) + 1;

        // Return new root
        return x;
    }
    private int getBalance(Node root) {
        return root.left.height - root.right.height;
    }
    Node delete(Node root, int key){
        /* perform normal deletion in BST */
        if(key < root.value){
            root.left = delete(root.left, key);
        }
        else if(key > root.value){
            root.right = delete(root.right, key);
        }
        else{
            if(root.left == null && root.right == null){
                return null;
            }
            else if (root.left == null || root.right == null) {
                if(root.right!=null){
                    return root.right;
                }
                else{
                    return root.left;
                }
            }
            else{
                Node ins = inOrderSuccessor(root.right);
                root.value = ins.value;
                root.right = delete(root.right, ins.value);
            }
        }


        return root;
    }

    private Node inOrderSuccessor(Node root) {
        while(root.left!=null){
            root = root.left;
        }
        return root;
    }
}
