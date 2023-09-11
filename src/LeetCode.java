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
    public List<List<Integer>> groupThePeople(int[] arr) {
        List<List<Integer>> ans = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < arr.length; i++){
            if(map.containsKey(arr[i])){
                if(map.get(arr[i]).size() == arr[i]){
                    ans.add(new ArrayList<>(map.get(arr[i])));
                    map.get(arr[i]).clear();
                    map.get(arr[i]).add(i);
                }
                else{
                    map.get(arr[i]).add(i);
                }
            }
            else{
                map.put(arr[i], new ArrayList<>());
                map.get(arr[i]).add(i);
            }
        }
        return ans;
    }
}

public class LeetCode {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] llArr = {1,2,3,4,5};
        ListNode head = getLL(llArr);

        int[] arr = {3,3,3,3,3,1,3};
        System.out.println(s.groupThePeople(arr));
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
