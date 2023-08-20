import java.util.Arrays;
import java.util.Random;

public class QuickSelectAlgorithm {
    public static void main(String[] args) {
        int[] myArr = {1540, 2, 1, 7, 53, 14, 770};       //1 2 7 14 53 770 1540
        int k = myArr.length/2;
        System.out.println("k is " + k);
        System.out.println(quickSelect(myArr, 0, myArr.length-1, k));

    }
    static int quickSelect(int[] arr, int first, int last, int k){
        if(first<=last) {
            if (first == last) {
                return arr[first];
            }
            int down = first, up = last;
            int pivotI = getRandomIndex(arr, first, last);
            swap(arr, first, pivotI);
            System.out.println(Arrays.toString(arr));
            int pivot = first;
            while (down <= up) {
                while (down <= last && arr[pivot] >= arr[down]) {
                    down++;
                }
                while (up >= first && arr[pivot] < arr[up]) {
                    up--;
                }
                if (down < up) {
                    swap(arr, up, down);
                }
            }
            if(arr[up]<arr[pivot]){
                swap(arr, pivot, up);
            }
//            int mid = arr.length / 2;
            if (pivot == k) {
                return arr[pivot];
            } else if (pivot < k) {
                return quickSelect(arr, pivot + 1, last, k);
            } else {
                return quickSelect(arr, first, pivot, k);
            }
        }
        return 0;
    }
    private static int getRandomIndex(int[] arr, int first, int last){
//        Random random = new Random();
//        int pivot = (first) + (int)(Math.floor(Math.random()*(last-first+1)));
//        System.out.println(last + " " + first + " " + pivot) ;
//        swap(arr, pivot, first);
        return (first) + (int)(Math.floor(Math.random()*(last-first+1)));
    }
    private static void swap(int[] arr, int first, int last){
        int temp = arr[first];
        arr[first] = arr[last];
        arr[last] = temp;
    }
}
