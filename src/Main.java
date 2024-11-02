import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        LocalDate start = LocalDate.of(2024,1,17);
        LocalDate end = LocalDate.of(2024,1,24);
        Set<LocalDate> dates = Stream.iterate(start, date -> date.plusDays(1))
                .limit(ChronoUnit.DAYS.between(start, end))
                .collect(Collectors.toSet());
        System.out.println(dates);
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