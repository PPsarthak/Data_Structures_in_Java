import com.dataStructures.SLL;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
class Solution{
    public ListNode reverseKGroup(ListNode head, int k) {

//base case
        if(head == null || head.next == null) return head;
        if(k == 1) return head;

//initialize dummy pointer
        ListNode newHead = new ListNode(-1);
        newHead.next = head;

//pointers for reversal
        ListNode startPtr = head;
        ListNode endPtr = head;
        int start = 1;
        int end = 1;
        ListNode prevNode = newHead;
        ListNode nextNode = endPtr.next;

//traversing and reversing
        while(endPtr!=null){
            if(end-start+1 == k){
                System.out.println(startPtr.val + " " + endPtr.val);
                // System.out.println(prevNode.val + " " + nextNode.val);

                //reverse
                newHead = reverse(newHead,startPtr,endPtr,prevNode,nextNode);

                //update after reversing
                prevNode = startPtr;
                startPtr = nextNode;
                endPtr = startPtr;
                nextNode = nextNode.next;
                start = end+1;
                end = end+1;

            }
            else{
                // not to reverse
                endPtr = endPtr.next;
                if(endPtr!=null) nextNode = endPtr.next;
                end++;
            }
        }
        return newHead;
    }
    ListNode reverse(ListNode head, ListNode p, ListNode q, ListNode start, ListNode end){
        ListNode prev = p;
        ListNode curr = p.next;
        ListNode next = null;
        if(curr!=null && curr.next!=null) next = curr.next;

        p.next = end;
        while(curr!=null && curr!=end){
            //reverse
            curr.next = prev;

            //inc
            prev = curr;
            curr = next;
            if(curr!=null) next = curr.next;
        }
        if(start!=null) start.next = q;
        // System.out.println(curr.next.val);
        return prev;
    }
}

public class LeetCode {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] llArr = {1,2,3,4,5};
        ListNode head = getLL(llArr);

    }
    private static ListNode getLL(int[] array){
        if (array == null || array.length == 0) {
            return null;
        }

        ListNode head = new ListNode(array[0]);
        ListNode current = head;

        for (int i = 1; i < array.length; i++) {
            ListNode newNode = new ListNode(array[i]);
            current.next = newNode;
            current = newNode;
        }

        return head;
    }
    private static void printLL(ListNode head){
        if(head == null){
            System.out.println("Empty list");
            return;
        }
        System.out.print("The Linked List is: ");
        ListNode temp = head;
        while(temp!=null && temp.next!=null){
            System.out.print(temp.val + "->");
            temp = temp.next;
        }
        System.out.println(temp.val);
    }
}
