import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Deque {
    public static void main(String[] args) {
        //typical queue uses linked list + two internal pointers: head & tail
        Queue<Integer> myQ = new LinkedList<>();
        myQ.offer(5);
        myQ.offer(8);
        myQ.offer(7);
        System.out.println(myQ.peek());
        myQ.poll();
        System.out.println(myQ.peek());


        //DEQ uses an array because insertion & deletion will happen from both ends
        ArrayDeque<Integer> myDEQ = new ArrayDeque<>();
        myDEQ.offerFirst(5);
        myDEQ.offerFirst(8);
        myDEQ.offerFirst(7);
        myDEQ.offerLast(6);
        System.out.println(myDEQ);

    }
}
