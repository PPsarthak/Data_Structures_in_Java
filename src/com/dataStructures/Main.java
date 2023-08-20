package com.dataStructures;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        SLL mySLL = new SLL();
        /*
        mySLL.insertFirst(5);
        mySLL.insertEnd(8);
        mySLL.insertEnd(11);
        mySLL.insertEnd(15);
        mySLL.insertFirst(2);
        mySLL.insertMidIndex(3,9);
        mySLL.insertMidValue(11,12);
        mySLL.printLL();
        System.out.println(mySLL.searchNode(5));
        System.out.println(mySLL.searchNode(100));
        mySLL.reverseIterativeSLL(mySLL.getHead());
        mySLL.deleteSLL();
        mySLL.printLL();
        */
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(50);
        bst.insert(60);
        bst.insert(40);
        bst.insert(70);
        bst.inOrder(bst.root);
        System.out.println();

        bst.printRoot2Leaf(bst.root, new ArrayList<>());
    }
}
