import java.util.Arrays;

public class SmallestSubarrayToSort {
    public static int minSubarraysToSort(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int left = 0;
        while (left < n && nums[left] == sorted[left]) {
            left++;
        }

        int right = n - 1;
        while (right >= 0 && nums[right] == sorted[right]) {
            right--;
        }

        if (left >= right) {
            return 1; // Already sorted
        }

        int minSubarrays = 1;
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] < nums[i - 1]) {
                minSubarrays++;
            }
        }

        return minSubarrays+1;
    }
    public static int help(int[] arr){
        int ans = 1;
        int i = 1;
        while(i<arr.length){
            if(arr[i] < arr[i-1]){
                ans++;
            }
            else i++;
        }

        return ans;
    }
    public static void main(String[] args) {
        int[] input = {17, 13, 16, 9, 10, 12, 25, 23};
//        int[] input = {1,2,4};
        System.out.println("Smallest subarray length to sort: " + minSubarraysToSort(input));
        System.out.println("Smallest subarray length to sort: " + help(input));
    }
}
