package com.dataStructures;


// Definition for singly-linked list.
 class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

public class MiddleLinkedList {
    public ListNode middleNode(ListNode head) {
        int size = getSize(head);
        int mid = size/2;
        ListNode temp = head;
        while(mid!=0){
            temp = temp.next;
            mid--;
        }
        return temp;
    }
    int getSize(ListNode head){
        ListNode temp = head;
        int count = 0;
        while(temp!=null){
            temp = temp.next;
            count++;
        }
        return count;
    }
}
