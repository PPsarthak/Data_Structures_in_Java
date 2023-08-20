package com.dataStructures;

public class SLL {
    private Node head;
    private Node tail;
    private int size;

    public SLL() {
        this.size = 0;
    }

    //method to insert at start assuming head and tail may or may not be null
    //worst case implementation included
    public void insertFirst(int value){
        Node n = new Node(value);
        if (head != null)  n.next = head;
        head = n;
        size++;
        if (tail==null) tail = head;
    }

    //method to insert at end
    public void insertEnd(int value){
        Node n = new Node(value);
        if (tail!=null) tail.next = n;
        tail = n;
        size++;
        if (head == null) head = tail;
    }

    //method to insert node in the middle - based on the index
    public void insertMidIndex(int index, int value){
        if(index>size) insertEnd(value);
        else if (index==0) insertFirst(value);
        else{
            int count = 0;
            Node n = new Node(value);
            Node temp = head;
            while(count<index-1){
                temp = temp.next;
                count++;
            }
            n.next = temp.next;
            temp.next = n;
            size++;
        }
    }

    //method to insert at mid after a given value in the linked list
    public void insertMidValue(int v, int value){
        Node temp = head;
        while(temp!=tail){
            if(temp.data == v){
                Node n = new Node(value);
                n.next = temp.next;
                temp.next = n;
                size++;
                return;
            }
            temp = temp.next;
        }
        System.out.println("The value after which new node is to be inserted does not exist!");
    }

    //method to delete a node - based on value
    public void deleteNode(int value){
        if(head.data == value){    //if we want to delete head
            deleteFirst();
            return;
        }
        if(tail.data == value){  //if we want to delete tail
            deleteLast();
            return;
        }
        Node temp = head;
        Node prev = head;
        while (temp != tail) {
            if (temp.data == value) {
                prev.next = temp.next;
                size--;
                return;
            }
            prev = temp;
            temp = temp.next;
        }
        System.out.println("The element does not exist in the linked list");
    }

    public void deleteLast(){
        Node temp = head;
        while(temp.next!=tail){
            temp = temp.next;
        }
        tail = temp;
        size--;
    }

    public void deleteFirst(){
        if(head.next == tail){
            head = null;
            size--;
            return;
        }
        head = head.next;
        if(head==null) tail = null;
        size--;
    }

    //method to search a node in the linked list
    public boolean searchNode(int value){
        Node temp = head;
        while (temp!=tail){
            if(temp.data==value) return true;
            temp = temp.next;
        }
        return false;
    }

    //method to reverse the linked list - iteration
    public void reverseIterativeSLL(Node h){
        Node p = head;
        Node curr = h;
        Node prev = null;
        Node next = head.next;
        while (curr.next!=null){
            //reversing code
            curr.next = prev;
            //traversing code
            prev = curr;
            curr = next;
            next = next.next;
        }
        curr.next = prev;
        prev = curr;

        head = prev;
        tail = p;
    }

    //method to reverse the linked list - recursion

    public void deleteSLL(){
        head = null;
    }
    public void printLL(){
        if(head==null || tail==null){
            System.out.println("Linked List is empty");
            return;
        }
        System.out.print("The Linked List is: ");
        Node temp = head;
        while(temp!=tail){
            System.out.print(temp.data + "->");
            temp = temp.next;
        }
        System.out.println(temp.data);
        sizeLL();
    }

    public void sizeLL(){
        System.out.println("The size of the Linked List is : " + getSize());

    }

    public int getSize() {
        return size;
    }

    public Node getHead() {
        return head;
    }

    private class Node{
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
