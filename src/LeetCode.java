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
    public int maximumScore(int[] nums, int k) {
        int ans = nums[k];
        int min = nums[k];

        int i=k, j=k;

        while(i >=0 || j<nums.length){
            if(i == 0) j++;
            else if(j == nums.length-1) i--;
            else if(nums[i-1] > nums[j+1]){
                i--;
                min = Math.min(min, nums[i]);
            }
            else{
                j++;
                min = Math.min(min, nums[j]);
            }
            ans = Math.max(min*(j-i+1), ans);
        }
        return ans;
    }

}

public class LeetCode {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] llArr = {1,2,3,4,5};
        ListNode head = getLL(llArr);

        System.out.println(solution.maximumScore(new int[]{1,4,3,7,4,5}, 3));
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
