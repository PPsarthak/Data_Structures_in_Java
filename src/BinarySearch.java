import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BinarySearch {
    public static int binarySearch(int[] arr, int key){
        int start = 0, end = arr.length - 1;
        while (start<=end){
            int mid = start + (end-start)/2;
            if(arr[mid] == key) return mid;
            else if (arr[mid]>key) end = mid-1;
            else start = mid+1;
        }

        return -1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] myArr = {1,5,8,14,19,24,26};
        System.out.println(Arrays.toString(myArr));
        System.out.println("Enter a key: ");
        int key = Integer.parseInt(br.readLine());
        System.out.println("The key is present at index: " + binarySearch(myArr,key));

    }
}
