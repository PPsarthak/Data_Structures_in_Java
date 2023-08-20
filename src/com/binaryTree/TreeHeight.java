package com.binaryTree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class TreeHeight {
    static class Node{
        int data;
        Node left, right;
        public Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    public static int height(Node root){
        if(root == null){
            return 0;
        }

        int LH = height(root.left);
        int RH = height(root.right);
        return Math.max(LH,RH) +1;
    }

    public static int count(Node root){
        if(root == null) return 0;
        int LC = count(root.left);
        int rc = count(root.right);
        return LC+rc+1;
    }

    public static int sum(Node root){
        if(root == null ) return 0;
        int LS = sum(root.left);
        int RS = sum(root.right);
        return LS+RS+root.data;
    }

    public static boolean isSubtree(Node root, Node subRoot){
        if(root == null) return false;
        if(root.data == subRoot.data){
            if(isIdentical(root,subRoot)){
                return true;
            }
        }
        boolean LA = isSubtree(root.left,subRoot);
        boolean RA = isSubtree(root.right,subRoot);

        return LA||RA;
    }

    public static boolean isIdentical(Node node, Node subRoot){
        if(node == null && subRoot == null) return true;

        if(node == null || subRoot == null || node.data != subRoot.data) return false;

        if(!isIdentical(node.left, subRoot.left)) return false;
        if(!isIdentical(node.right, subRoot.right)){
            return false;
        }

        return true;
    }

    static class Info{
        Node node;
        int hd;
        public Info(Node node, int hd){
            this.node = node;
            this.hd = hd;
        }
    }
    public static void topView(Node root) {
        Queue<Info> q = new LinkedList<>();
        HashMap<Integer, Node> map = new HashMap<>();
        int min = 0, max = 0;
        q.add(new Info(root, 0));
        q.add(null);

        while (!q.isEmpty()) {
            Info curr = q.remove();
            if (curr == null) {
                if (q.isEmpty()) {
                    break;
                } else {
                    q.add(null);
                }
            } else {
                if (!map.containsKey(curr.hd)) {
                    map.put(curr.hd, curr.node);
                }

                if (curr.node.left != null) {
                    q.add(new Info(curr.node.left, curr.hd - 1));
                    min = Math.min(min, curr.hd - 1);
                }

                if (curr.node.right != null) {
                    q.add(new Info(curr.node.right, curr.hd + 1));
                    max = Math.max(max, curr.hd + 1);
                }
            }
        }

        for (int i = min; i <= max; i++) {
            System.out.print(map.get(i).data + " ");
        }
        System.out.println("");
    }

    static void kLevel(Node root, int level,int k){
        if(root == null){
            return;
        }
        if(level == k){
            System.out.print(root.data+" ");
            return;
        }

        kLevel(root.left,level+1,k);
        kLevel(root.right,level+1,k);
    }
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
//        System.out.println(height(root));
//
//        System.out.println(count(root));
//        System.out.println(sum(root));

        Node subRoot = new Node(2);
        subRoot.left = new Node(4);
//        subRoot.right = new Node(5);

//        System.out.println(isSubtree(root,subRoot));
        topView(root);

        int k = 2;
        kLevel(root,1,k);
    }
}


