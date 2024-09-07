import com.dataStructures.SLL;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("123456");
        sb.replace(3,4,"#");
        System.out.println(sb);

        String s = "label";
        List<String> ans = new ArrayList<>();
        for(char c : s.toCharArray()){
            ans.add(String.valueOf(c));
        }
        Stack<Integer> st = new Stack<>();
    }

    static void add(int a, int b){
        a = a+1;
        b = b+1;
    }
    static void add(int[] a, int[] b){
        a[2] = a[2]+1;
        b[2] = b[2]+1;
    }
}