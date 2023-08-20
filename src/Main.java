import com.dataStructures.SLL;
import java.util.*;
class ListNode {
    int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

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

public class Main {
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
    public static void main(String[] args) {
//        ListNode head = getLL(myArr);
//        while(head!=null){
//            System.out.println(head.val);
//            head = head.next;
//        }

    }
    static int[] maxSlidingWindow(int[] nums, int k) {
        int[] ans = new int[nums.length+k+k-2];

        int[] temp = new int[nums.length-2];

        for(int i=k-1, j=0; i<ans.length-k+1; j++, i++){
            ans[i] = nums[j];
        }
//
        System.out.println(Arrays.toString(ans));
//
        int sum = 0;
        for(int i=0; i<k; i++){
            sum+=ans[i];
        }
//
        System.out.println(sum);
//
        int prevSum = sum;
        temp[0] = sum;
        for(int i=k; i<ans.length; i++){
            sum-=ans[i-k+1];
            sum+=ans[i];
            int z = (i-1)/k;
            System.out.println(z);
            temp[z] = Math.max(temp[z], (sum-prevSum));
        }
        return temp;
    }
}