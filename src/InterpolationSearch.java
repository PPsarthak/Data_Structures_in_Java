public class InterpolationSearch {
    public static void main(String[] args) {

    }
    public static int interpolationSearch(int[] array, int K) {
        int low = 0, high = array.length-1;

        while (low <= high && K >= array[low] && K <= array[high]) {

            int index = low + ((K - array[low]) * (high - low)) / (array[high] - array[low]);

            if (array[index] < K) {
                low = index + 1;
            } else if (array[index] > K) {
                high = index - 1;
            } else {
                // found K
                return index;
            }
        }

        return -1;
    }
}
