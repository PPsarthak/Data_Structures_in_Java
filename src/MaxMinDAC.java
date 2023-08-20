import java.util.Arrays;

public class MaxMinDAC {
    private static int[] getArray(int size){
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) Math.floor(Math.random()*1000);
        }
        return arr;
    }
    public static void main(String[] args) {
        int[] myArr = getArray(15);
        System.out.println("Random generated array is: " + Arrays.toString(myArr));
        int[] maxArr = getMax(myArr, 0, myArr.length-1, myArr[0]);
        System.out.println("Maximum and 2nd maximum in the array is " + Arrays.toString(maxArr) + " respectively");
    }
    static int[] getMax(int[] arr, int first, int last, int max){
        int[] ans = new int[2];
        ans[0] = arr[first];
        ans[1] = arr[first];
        if(first==last){
            return ans;
        }
        else if (first==last-1) {
            ans[0] = Math.max(arr[first], arr[last]);
            ans[1] = Math.min(arr[first], arr[last]);
            return ans;
        }
        else{
            int mid = first + (last-first)/2;
            int[] max1 = getMax(arr, first, mid, max);
            int[] max2 = getMax(arr, mid+1, last, max);
            ans[0] = Math.max(max1[0], max2[0]);
            ans[1] = Math.min(max1[0], max2[0]);
            return ans;
        }
    }
}
