public class KadaneAlgorithm {
    public static void main(String[] args) {
        int[] myArr = {1,5,233,7};

    }
    static int maxProduct(int[] nums) {
        int maxProduct = Integer.MIN_VALUE, currentProduct = 1;
        for(int i = 0; i<nums.length; i++){
            currentProduct*=nums[i];
            if(currentProduct>maxProduct){
                maxProduct = currentProduct;
            }
            if(currentProduct==0){
                currentProduct = 1;
            }
        }
        return maxProduct;
    }
    static void maxSubArraySum(int a[]){
        int maxSum = Integer.MIN_VALUE, currentSum = 0;
        int start = 0, end = 0, s = 0;

        for (int i = 0; i < a.length; i++) {
            currentSum += a[i];

            if (maxSum < currentSum) {
                maxSum = currentSum;
                start = s;
                end = i;
            }

            if (currentSum < 0) {
                currentSum = 0;
                s = i + 1;
            }
        }
        System.out.println("Maximum contiguous sum is " + maxSum);
        System.out.println("Starting index " + start);
        System.out.println("Ending index " + end);
    }
}
