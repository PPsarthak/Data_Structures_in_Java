import java.util.Collections;
import java.util.PriorityQueue;

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
        System.out.println(maxPQ);
        maxPQ.remove(10);
        System.out.println(maxPQ);
        maxPQ.remove(45);
        System.out.println(maxPQ);
        System.out.println("Peeking max heap gives the max " + maxPQ.poll());

    }
}
