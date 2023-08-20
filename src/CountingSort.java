import java.util.Arrays;

public class CountingSort {
    public static void main(String[] args) {
        int[] myArr = {10,10,23,23,12,80};
        countingSort(myArr);
        System.out.println(Arrays.toString(myArr));
    }
    public static void countingSort(int[] arr){
        int[] ans = new int[arr.length];
        int[] aux = new int[getMax(arr)+1];
        for (int i = 0; i < arr.length; i++) {
            aux[arr[i]]++;
        }
        System.out.println(Arrays.toString(aux));

        for (int i = 1; i < aux.length; i++) {
            aux[i] += aux[i-1];
        }

        for (int i = ans.length-1; i >= 0; i--) {
            ans[aux[arr[i]]-1] = arr[i];
            aux[arr[i]]--;
        }
        for (int i = 0; i < ans.length; i++) {
            arr[i] = ans[i];
        }
    }
    public static int getMax(int[] arr){
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        return max;
    }
}
