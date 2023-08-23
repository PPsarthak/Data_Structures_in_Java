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
    public int deepestLeavesSum(TreeNode root) {
        if(root==null) return 0;
        return sumNodes(root);
    }
    public int sumNodes(TreeNode root){
        if (root == null){
            return 0;
        }
        int leftSum = sumNodes(root.left);
        int rightSum = sumNodes(root.right);
        return Math.max(leftSum, rightSum);
    }
    //Q 480 - Sliding Window Median (Hard)
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] ans = new double[nums.length-k+1];
        if(k==1){
            for(int i=0; i<nums.length; i++){
                ans[i] = nums[i];
            }
            return ans;
        }
        List<Integer> myVec = new Vector<>();
        int index = 0;
        for(int i=0; i<k; i++){
            int j = myVec.size()-1;
            boolean flag = false;
            while(j>=0 && myVec.get(j)<nums[i]){
                j--;
                flag = true;
            }
            if(flag){
                myVec.add(j+1, nums[i]);
            }
            else{
                myVec.add(nums[i]);
            }
        }
        int idx = myVec.size()/2;
        if(myVec.size()%2!=0){
            ans[index++] = (double)myVec.get(idx);
        }
        else{
            ans[index++] = (double)(myVec.get(idx)+ myVec.get(idx-1))/2;
        }
        System.out.println(myVec);
        for(int i=k; i<nums.length; i++){
            myVec.remove((Integer) nums[i - k]);
            System.out.println("Shifting slide" + myVec);
            int j = myVec.size()-1;
            boolean flag = false;
            while(j>=0 && myVec.get(j)<nums[i]){
                j--;
                flag = true;
            }
            if(flag){
                myVec.add(j+1, nums[i]);
            }
            else{
                myVec.add(nums[i]);
            }
            if(myVec.size()%2!=0){
                ans[index++] = (double)myVec.get(idx);
            }
            else{
                ans[index++] = (double)(myVec.get(idx)+ myVec.get(idx-1))/2;
            }
        }
        return ans;
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
        while(temp!=null){
            System.out.print(temp.val + "->");
            temp = temp.next;
        }
        System.out.println(temp.val);
    }
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] llArr = {1,1,1,2,3};

    }
}
