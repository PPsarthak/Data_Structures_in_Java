import java.util.Arrays;

public class SortingAlgos_01 {
    public static void main(String[] args) {
        int[] array = {-23,18,5,91,3,-2,1,4,-11,2,0,45,37,-8};
        System.out.println("Bubble Sort: " + Arrays.toString(bubble(array)));
        System.out.println("Selection Sort: " + Arrays.toString(selection(array)));
        System.out.println("Insertion Sort: " + Arrays.toString(insertion(array)));
        System.out.println(Arrays.toString(cyclic(new int[]{4,3,2,7,8,2,3,1})));

    }
    static int[] cyclic(int[] arr){
        int i = 0;
        while(i < arr.length){
            int correct = arr[i]-1;
            if (arr[i]!=arr[correct]){
                swap(arr, i, correct);
            }
            else{
                i++;
            }
        }
        return arr;
    }
    static int[] insertion(int[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i+1; j > 0; j--) {
                if(arr[j] < arr[j-1]) swap(arr, j, j-1);
                else break;
            }
        }
        return arr;
    }
    static int[] selection(int[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            int lastIndex = arr.length-i-1;
            int maxIndex = getMax(arr, 0, lastIndex);
            swap(arr, maxIndex, lastIndex);
        }
        return arr;
    }
    static int[] bubble(int[] arr){
        boolean flag = false;
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 1; j < arr.length - i; j++) {
                if(arr[j]<arr[j-1]){
                    swap(arr, j, j-1);
                    flag = true;
                }
            }
            if (!flag){
                break;
            }
        }
        return arr;
    }

    //non sorting methods
    static int[] swap (int[] arr, int first, int second){
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
        return arr;
    }
    static int getMax(int[] arr, int first, int last){
        int max = first;
        for (int i = first; i <= last; i++) {
            if(arr[i]>arr[max]) max = i;
        }
        return max;
    }
}
