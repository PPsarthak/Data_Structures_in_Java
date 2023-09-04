package com.dataStructures;

import java.util.*;

/**
 * My Code for Binary Tree
 */
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
    static Node buildTree(int[] nodes){
        index++;
        if(nodes[index]==-1) return null;
        Node root;
        root = new Node(nodes[index]);
        root.left = buildTree(nodes);
        root.right = buildTree(nodes);
        return root;
    }
    static Node constructTree(Integer[] array) {
        if (array == null || array.length == 0) {
            return null;
        }

        Queue<Node> queue = new LinkedList<>();
        Node root = new Node(array[0]);
        queue.offer(root);

        for (int i = 1; i < array.length; i++) {
            Node parent = queue.poll();
            if (array[i] != null) {
                parent.left = new Node(array[i]);
                queue.offer(parent.left);
            }
            i++;
            if (i < array.length && array[i] != null) {
                parent.right = new Node(array[i]);
                queue.offer(parent.right);
            }
        }

        return root;
    }
    //Time Complexity for any traversal = O(n)
    static void preOrder(Node root){
        if(root == null) return;
        System.out.print(root.value + " ");
        preOrder(root.left);
        preOrder(root.right);
    }
    //iterative preorder traversal
    static List<Integer> preOrderIt(Node root){
        List<Integer> preOrder = new ArrayList<>();
        Stack<Node> myStack = new Stack<>();
        if (root == null) return preOrder;
        myStack.push(root);
        Node temp = null;
        while(!myStack.isEmpty()){
            temp = myStack.pop();
            preOrder.add(temp.value);
            if(temp.right!=null) myStack.push(temp.right);
            if(temp.left!=null) myStack.push(temp.left);
        }
        return preOrder;
    }
    static void inOrder(Node root){
        if(root == null) return;
        inOrder(root.left);
        System.out.print(root.value + " ");
        inOrder(root.right);
    }
    //iterative inorder traversal
    static List<Integer> inOrderIt(Node root){
        List<Integer> inorder = new ArrayList<>();
        Stack<Node> myStack = new Stack<>();
        if(root == null) return inorder;
        myStack.push(root);
        Node temp = root.left;
        while (true){
            if(temp!=null){
                myStack.push(temp);
                temp = temp.left;
            }
            else{
                if(myStack.isEmpty()) break;
                temp = myStack.pop();
                inorder.add(temp.value);
                temp = temp.right;
            }
        }
        return inorder;
    }
    static void postOrder(Node root){
        if(root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.value + " ");
    }
    //iterative post order traversal
    static List<Integer> postOrderIt2(Node root){
        List<Integer> postOrder = new ArrayList<>();
        Stack<Node> myStack  = new Stack<>();
        Stack<Integer> myStack2  = new Stack<>();
        if(root == null) return postOrder;
        myStack.push(root);
        Node temp = null;
        while(!myStack.isEmpty()){
            temp = myStack.pop();
            myStack2.push(temp.value);
            if(temp.left!=null) myStack.push(temp.left);
            if(temp.right!=null) myStack.push(temp.right);
        }
        while (!myStack2.isEmpty()) postOrder.add(myStack2.pop());
        return postOrder;
    }
    //iterative post order using 1 stack
    static List<Integer> postOrderIt1(Node root) {
        List<Integer> postOrder = new ArrayList<>();
        Stack<Node> myStack = new Stack<>();

        if (root == null)
            return postOrder;

        Node current = root;
        Node lastVisited = null;

        while (!myStack.isEmpty() || current != null) {
            if (current != null) {
                myStack.push(current);
                current = current.left;
            } else {
                Node peekNode = myStack.peek();

                if (peekNode.right != null && lastVisited != peekNode.right) {
                    current = peekNode.right;
                } else {
                    myStack.pop();
                    postOrder.add(peekNode.value);
                    lastVisited = peekNode;
                }
            }
        }

        return postOrder;
    }
    //better approach for BFS traversal
    static List<List<Integer>> levelOrder2(Node root){
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
    static void levelOrder(Node root){
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
    static List<List<Integer>> zigzag(Node root){
        Queue<Node> q = new LinkedList<>();
        List<List<Integer>> zigzagList = new ArrayList<>();
        if(root == null) return zigzagList;
        boolean leftRight = true;
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> myList = new Vector<>(size); // Initialize Vector with the appropriate size
            for (int i = 0; i < size; i++) {
                if(q.peek().left!=null) q.offer(q.peek().left);
                if(q.peek().right!=null) q.offer(q.peek().right);
                if (leftRight) {
                    myList.add(q.poll().value);
                } else {
                    myList.add(0, q.poll().value); // Add to the beginning for reverse order
                }
            }
            leftRight = !leftRight;
            zigzagList.add(myList);
        }
        return zigzagList;
    }
    //only used for topView of binary tree
    private static class topViewPair{
        Node node;
        int level;
        topViewPair(Node root, int level){
            this.node = root;
            this.level = level;
        }
    }
    static List<Integer> topView(Node root){
        List<Integer> topViewList = new ArrayList<>();
        if(root == null) return topViewList;
        Map<Integer, Integer> myMap = new TreeMap<>();
        Queue<topViewPair> q = new LinkedList<>();
        q.offer(new topViewPair(root, 0));
        while (!q.isEmpty()){
            //getting the front of the q - destructuring node and level
            topViewPair n = q.poll();
            Node temp = n.node;
            int lvl = n.level;
            //if that level is not present, add it to map
            if(!myMap.containsKey(lvl)){
                myMap.put(lvl, temp.value);
            }
            //adding the left and right children, with proper levels
            if(temp.left!=null) q.offer(new topViewPair(temp.left, lvl-1));
            if(temp.right!=null) q.offer(new topViewPair(temp.right, lvl+1));
        }
        //adding the nodes in the list in sorted manner
        for(Integer i : myMap.keySet()){
            topViewList.add(myMap.get(i));
        }
        return topViewList;
    }
    private static class verticalOrderPair{
        Node node;
        int verticalLevel;
        int horizontalLevel;
        verticalOrderPair(Node root, int verticalLevel, int horizontalLevel) {
            this.node = root;
            this.verticalLevel = verticalLevel;
            this.horizontalLevel = horizontalLevel;
        }
    }
    static List<List<Integer>> verticalOrder(Node root){
        List<List<Integer>> verticalOrderList = new ArrayList<>();
        if(root == null) return verticalOrderList;

        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> myMap;
        //TreeMap stores < Vertical line no, Horizontal level, Nodes (sorted) >
        // left to right -> sorted
        myMap = new TreeMap<>();

        Queue<verticalOrderPair> q = new LinkedList<>();
        q.offer(new verticalOrderPair(root, 0, 0));

        while(!q.isEmpty()){
            verticalOrderPair v = q.poll();
            int ver = v.verticalLevel;
            int hor = v.horizontalLevel;
            if(!myMap.containsKey(ver)){
                //this means - we are first time visiting this vertical line
                myMap.put(ver, new TreeMap<>());
                //hence we add it to myMap
            }
            if(!myMap.get(ver).containsKey(hor)){
                //this will always execute when above if is executed
                myMap.get(ver).put(hor, new PriorityQueue<>());
            }
            myMap.get(ver).get(hor).offer(v.node.value);
            //executed in 2 cases: when new entry is added no PQ must be filled
            // or when both statements are not executed, and we need to append new value
            if(v.node.left!=null) q.offer(new verticalOrderPair(v.node.left, ver-1, hor+1));
            if(v.node.right!=null) q.offer(new verticalOrderPair(v.node.right, ver+1, hor+1));

        }
        for(TreeMap<Integer, PriorityQueue<Integer>> i : myMap.values()){
            List<Integer> temp = new ArrayList<>();
            for(PriorityQueue<Integer> p : i.values()){
                while(!p.isEmpty()){
                    temp.add(p.poll());
                }
            }
            verticalOrderList.add(temp);
        }
        return verticalOrderList;
    }
    static List<Integer> boundaryTraversal(Node root){
        List<Integer> myList = new ArrayList<>();
        myList.add(root.value);
        if(isLeaf(root)){
            return myList;
        }
        addLeftNodes(root.left, myList);
        addLeafNodes(root, myList);
        addRightNodes(root.right, myList);
        return myList;
    }
    static void addLeftNodes(Node root, List<Integer> myList){
        while(root!=null){
            if(!isLeaf(root)) myList.add(root.value);
            else return;
            if(root.left!=null) root = root.left;
            else root = root.right;
        }
    }
    static void addLeafNodes(Node root, List<Integer> myList){
        if(isLeaf(root)){
            myList.add(root.value);
            return;
        }
        if(root.left!=null) addLeftNodes(root.left, myList);
        if(root.right!=null) addLeafNodes(root.right, myList);
    }
    static void addRightNodes(Node root, List<Integer> myList){
        Stack<Integer> myStack = new Stack<>();
        while (root!=null){
            if(!isLeaf(root)) myStack.push(root.value);
            if(root.right!=null) root = root.right;
            else root = root.left;
        }
        while (!myStack.isEmpty()){
            myList.add(myStack.pop());
        }
    }
//    static List<Integer> rootToNodePath(Node root, )
    static boolean isLeaf(Node root){
        return root.left == null && root.right == null;
    }
    static int balancedTree(Node root){
        if(root == null) return 0;
        int leftTree = balancedTree(root.left);
        int rightTree = balancedTree(root.right);
        if(leftTree == -1 || rightTree == -1 ||Math.abs(leftTree-rightTree)>1) return -1;
        return Math.max(leftTree, rightTree) + 1;
    }
    static int countOfK = 0;
    static int kthSmallestEle = 0;
    static int kthSmallest(Node root, int k) {
        countOfK = k;
        getK(root);
        return kthSmallestEle;
    }
    static void getK(Node root){
        if(root == null) return;
        getK(root.left);
        countOfK--;
        if(countOfK == 0){
            kthSmallestEle = root.value;
            return;
        }
        getK(root.right);
    }
    static int countNodes(Node root){
        if (root == null){
            return 0;
        }
        int leftNodes = countNodes(root.left);
        int rightNodes = countNodes(root.right);
        return leftNodes + rightNodes + 1;
    }
    static int sumNodes(Node root){
        if (root == null){
            return 0;
        }
        int leftSum = sumNodes(root.left);
        int rightSum = sumNodes(root.right);
        return leftSum + rightSum + root.value;
    }
    static int getHeight(Node root){
        if (root == null){
            return 0;
        }
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        return Math.max(leftHeight,rightHeight)+1;
    }
    static boolean subTree(Node root, Node subRoot){
        if(subRoot == null) return true;
        if(root == null) return false;
        if(root.value == subRoot.value){
            if(subRoot.left == null && subRoot.right == null) return true;
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
        Integer[] myArr = {3,9,20,null,null,15,7};
        Node root = constructTree(myArr);
        //don't change above code
        inOrder(root);
        System.out.println(inOrderIt(root));
        System.out.println(zigzag(root));
    }
}


