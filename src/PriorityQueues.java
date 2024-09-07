import java.util.*;

public class PriorityQueues {
    public static void main(String[] args) {
        //by default, it internally uses min-heap
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();
        minPQ.add(5);
        minPQ.add(10);
        minPQ.add(15);
        minPQ.add(20);
        minPQ.add(25);
//        System.out.println("Peeking min heap gives the min " + minPQ.peek());

        //adding Collections.reverseOrder() makes it use max-heap
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());
        maxPQ.add(5);
        maxPQ.add(10);
        maxPQ.add(20);
        maxPQ.add(25);
//        System.out.println(maxPQ);
        maxPQ.remove(10);
//        System.out.println(maxPQ);
        maxPQ.remove(45);
//        System.out.println(maxPQ);
//        System.out.println("Peeking max heap gives the max " + maxPQ.poll());

        String s = "isawsquirrelnearmysquirrelhouseohmy";
        String a = "my";
        String b = "squirrel";
        int k = 15;

        //sliding window approach
        for(int i=0; i<s.length()-k; i++){
            //make window from 0 to k
            boolean aFlag = false;
            boolean bFlag = false;
            for(int j=i; j<k; j++){
                if(s.startsWith(a,j)) aFlag = true;
                if(s.startsWith(b,j)) bFlag = true;
            }
        }
    }
    public List<Integer> beautifulIndices(String s, String a, String b, int k) {
        List<Integer> ans  = new ArrayList<>();
        Set<Integer> set = new HashSet<>();

        for(int i=0; i<=s.length()-a.length(); i++){
            if(s.startsWith(a,i)){
                int lowerBound = Math.max(0, i-k);
                int upperBound = Math.min(s.length()-b.length(), i+k);

                for(int j=lowerBound; j<=upperBound; j++){
                    if(s.startsWith(b,j)){
                        if(!set.contains(i)){
                            ans.add(i);
                            set.add(i);
                        }
                    }
                }
            }
        }

        return ans;
    }
}
