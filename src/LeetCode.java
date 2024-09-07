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
    public int garbageCollection(String[] garbage, int[] travel) {
        int gTime = 0;
        int pTime = 0;
        int mTime = 0;

        for(int j=0; j<garbage.length; j++){
            String s = garbage[j];

            boolean gFlag = false;
            boolean pFlag = false;
            boolean mFlag = false;

            for(int i=0; i<s.length(); i++){
                char c = s.charAt(i);

                if(c == 'G'){
                    gTime++;
                    gFlag = true;
                }
                if(c == 'P'){
                    pTime++;
                    pFlag = true;
                }
                if(c == 'M'){
                    mTime++;
                    mFlag = true;
                }
            }
            if(j>0){
                if(gFlag) gTime+=travel[j-1];
                if(pFlag) pTime+=travel[j-1];
                if(mFlag) mTime+=travel[j-1];
            }
        }

        System.out.println(gTime);
        System.out.println(pTime);
        System.out.println(mTime);

        return 0;
    }
    int recursive(int index, char[] arr) {
        // base case
        if (index <= 0) {
            return 1;
        }

        if (arr[index] == '0') return recursive(index - 1, arr);

        // 2615
        int singleDigit = recursive(index - 1, arr);

        int twoDigit = 0;
        if (index > 0 &&
                (arr[index - 1] == '1' ||
                        (arr[index - 1] == '2' && arr[index] >= '0' && arr[index] <= '6'))) {
            twoDigit = recursive(index - 2, arr);
        }

        return singleDigit + twoDigit;
    }

}

public class LeetCode {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] llArr = {1,2,3,4,5};
        ListNode head = getLL(llArr);

        String[] garbage = {"G","P","GP","GG"};
        int[] travel = {2,4,3};

        String s = "226";
        char[] arr = s.toCharArray();
        System.out.println(solution.recursive(arr.length-1, arr));
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
