public class LinearSearch {
    public static void main(String[] args) {
        int[] myArr = {1,5,2,7,3,4,6};
        System.out.println(linearSearch(myArr, 5));
    }
    public static int linearSearch(int[] arr, int target){
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]==target) return i;
        }
        return -1;
    }
}
