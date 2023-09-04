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
    public int getMoneyAmount(int n) {
        int ans = 0;
        int start = 1, end  = n;
        while(start!=end-1){
            int mid = start + (end-start)/2;
            System.out.println(mid);
            ans+=mid;
            start = mid+1;
        }
        return ans;
    }
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head == null || head.next == null) return head;

        ListNode temp = head;
        int rcount = 1;
        while(rcount<right && temp!=null){
            temp = temp.next;
            rcount++;
        }
        System.out.println("temp: " + temp.val);

        //3 pointers - prev, curr and nex should reach required positions

        ListNode prev = null;
        ListNode curr = head;
        ListNode nex = head.next;
        ListNode xyz = null;
        int lcount = 1;    //update lcount each time cur moves
        while(lcount<=left && curr!=null){
            prev = curr;
            if(lcount+1 == left){
                xyz = curr;
            }
            curr = nex;
            nex = nex.next;
            lcount++;
        }
        System.out.println("prev: " + prev.val + " curr: " + curr.val);
        ListNode abc = prev;
        System.out.println("xyz: " + xyz.val);
        System.out.println("abc: " + abc.val);
        prev.next = temp.next;
        while(prev!=temp && curr!=null){
            nex = curr.next;
            curr.next = prev;

            // Move the pointers forward
            prev = curr;
            curr = nex;
            System.out.println("prev: " + prev.val + " curr: " + curr.val);

        }
        xyz.next = temp;

        return xyz;
    }
}

public class LeetCode {
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
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] llArr = {1,2,3,4,5};
        ListNode head = getLL(llArr);
        System.out.println(s.getMoneyAmount(10));
    }
}
