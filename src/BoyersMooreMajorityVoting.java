public class BoyersMooreMajorityVoting {
    public static int majorityElement(int[] nums) {
        int candidate = -1;
        int count = 0;
        for(int i: nums){
            if(count==0){
                candidate = i;
                // count++;
            }
            if(candidate == i){
                count++;
            }
            else{
                count--;
            }
        }
        if(count==0) return -1;
        else return candidate;
    }
}
