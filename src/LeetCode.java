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
    static int n;
    static int m;
    static int[][] arr;

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] grid = {{3,2,4},{2,1,9},{1,1,7}};
        System.out.println(maxMoves(grid));
    }

    public static int maxMoves(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        arr = grid;

        int ans = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                ans = Math.max(ans, get(i, j, -1));
            }
        }

        return ans;
    }
    static int get(int i, int j, int prev){
        if(i<0 || j<0 || i>=n || j>=m || arr[i][j] <= prev){
            System.out.println("Re: " + i + " " + j);
            return 0;
        }

        int up = get(i-1, j+1, arr[i][j]);
        int str = get(i, j+1, arr[i][j]);
        int down = get(i+1, j+1, arr[i][j]);

        System.out.println("s" + str + " u " + up + " do " + down);

        int maxMoves = 1+Math.max(str, Math.max(up, down));
        return maxMoves;
    }
}
