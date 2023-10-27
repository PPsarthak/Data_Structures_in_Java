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
    int temp = Integer.MAX_VALUE;
    public int[][] updateMatrix(int[][] mat) {
        int[][] ans = new int[mat.length][mat[0].length];

        for(int i=0; i<mat.length; i++){
            for(int j=0; j<mat[0].length; j++){
                if(mat[i][j]!=0){
                    System.out.println(i +" " + j);
                    ans[i][j] = myDFS(i, j, 0, mat);
                }
            }
        }

        return ans;
    }
    int myDFS(int i, int j, int count, int[][] grid){
        if(i<0 || j<0 || i>=grid.length || j>=grid[0].length){
            return (int)(1e8);
        }
        if(grid[i][j] == 0){
            return count;
        }
        int a = myDFS(i, j-1, count+1, grid);
        temp = Math.min(a, temp);

        int b = myDFS(i-1, j, count+1, grid);
        temp = Math.min(b, temp);

        int c = myDFS(i, j+1, count+1, grid);
        temp = Math.min(c, temp);

        int d = myDFS(i+1, j, count+1, grid);
        temp = Math.min(d, temp);

        return Math.min(a,Math.min(b, Math.min(c, d)));
    }

}

public class LeetCode {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] llArr = {1,2,3,4,5};
        ListNode head = getLL(llArr);

        int[][] grid = {{0,0,0}, {0,1,0}, {1,1,1}};
        System.out.println(Arrays.deepToString(solution.updateMatrix(grid)));
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
