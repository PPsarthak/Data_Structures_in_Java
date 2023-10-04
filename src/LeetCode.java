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
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> pacificAtlantic(int[][] graph) {
        for(int i=0; i<graph.length; i++){
            for(int j=0; j<graph[0].length; j++){

            }
        }
        return ans;
    }
    boolean pacific(int[][] graph, int i, int j, int prev){
        if(i<1 || j<1) return true;
        if(graph[i][j] < prev) return false;

        //top
        if(pacific(graph, i-1, j, graph[i][j])){
            return true;
        }

        //bottom
        if(pacific(graph, i+1, j, graph[i][j])){
            return true;
        }

        //right
        if(pacific(graph, i, j+1, graph[i][j])){
            return true;
        }

        //left
        if(pacific(graph, i, j-1, graph[i][j])){
            return true;
        }

        return false;
    }

}

public class LeetCode {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] llArr = {1,2,3,4,5};
        ListNode head = getLL(llArr);
        int[][] heights = {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        solution.pacificAtlantic(heights);
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
