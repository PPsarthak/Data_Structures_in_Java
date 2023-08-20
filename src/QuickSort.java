import java.util.Arrays;

public class QuickSort {
    static int[] myArr = {1,5,2,7,6,3,4,8};
    public static void main(String[] args){
        quickSort(myArr, 0, myArr.length-1);
        System.out.println(Arrays.toString(myArr));
    }
    public static void quickSort(int[] arr, int first, int last){
        if(first<last){
            int down = first, up = last;
            int pivot = first;
            while(down<up){
                while(arr[pivot]>arr[down] && down<last) down++;
                while(arr[pivot]<=arr[up] && up>first) up--;
                if(down<up) swap(arr, up, down);
            }
            swap(arr, pivot, up);
            quickSort(arr, first, up-1);
            quickSort(arr, up+1, last);
        }
    }
    private static void swap(int[] arr, int first, int last){
        int temp = arr[first];
        arr[first] = arr[last];
        arr[last] = temp;
    }
}
