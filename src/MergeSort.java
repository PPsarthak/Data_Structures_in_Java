import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] best = {1,2,3,4,5,6,7};
        int[] avg = {1,5,2,7,3,4,6};
        int[] worst = {7,6,5,4,1,2,3};

        mergeSort(best, 0, best.length-1);
        System.out.println(Arrays.toString(best));

        mergeSort(avg, 0, avg.length-1);
        System.out.println(Arrays.toString(avg));

        mergeSort(worst, 0, worst.length-1);
        System.out.println(Arrays.toString(worst));
    }
    public static void mergeSort(int[] arr, int first, int last){
        if(first<last){
            int mid = first + (last-first)/2;
            mergeSort(arr, first, mid);
            mergeSort(arr, mid+1, last);
            merge(arr, first, mid, last);
        }
    }
    public static void merge(int[] arr, int p, int q, int r){
        int[] arr1 = new int[q-p+1];
        int[] arr2 = new int[r-q];

        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = arr[p+i];
        }
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = arr[q+1+i];
        }

        int i = 0, j = 0, k = p;
        while(i < arr1.length && j < arr2.length){
            if(arr1[i]<arr2[j]) arr[k++] = arr1[i++];
            else arr[k++] = arr2[j++];
        }

        while(i < arr1.length){
            arr[k++] = arr1[i++];
        }
        while(j < arr2.length){
            arr[k++] = arr2[j++];
        }
    }
}

