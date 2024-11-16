import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAPI {
    public static void main(String[] args) {
        List<Integer> list = List.of(4,5,2,3,8,1,6);

        //A Stream represents a sequence of elements that can be processed in a pipeline.
        /*
        *  Immutable: Streams do not modify the original data source.
        *  One-time-use: A Stream cannot be reused after a terminal operation.
        *  Parallelism: Streams support parallel processing with parallelStream().
        *  Pipelining: Multiple operations can be chained in a declarative manner.
         */
        Stream<Integer> stream = list.stream();
        List<Integer> evenNumsDoubled = stream.filter(n -> n%2==0)
                .map(n -> n*2).collect(Collectors.toList());
        System.out.println(evenNumsDoubled);

        //PREDICATE: Functional Interface that has a test() which returns a true/false
        Predicate<Integer> checkEven = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer%2==0;
            }
        };
        System.out.println(checkEven.test(4));
        System.out.println(checkEven.test(5));

        //FUNCTION: Functional interface that has an apply() which is used to map values
        Function<Integer, String> numberToString = num -> "Number: " + num;
        System.out.println(numberToString.apply(5));
        System.out.println(numberToString.apply(4));

        //DIFFERENT FUNCTIONS
        //filter: used to filter data obviously....
        List<String> names = List.of("Alice", "Bob", "Charlie");
        List<String> filtered = names.stream()
                .filter(name -> name.startsWith("A"))
                .collect(Collectors.toList());
        System.out.println(filtered);

        //map: can be used to transform the contents of the data
        List<Integer> numbers = List.of(1, 2, 3);
        List<Integer> squared = numbers.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        System.out.println(squared);

        //flatMap: can be used to flatten the collection, like convert 2D list to 1D list
        List<List<Integer>> twoDimList = List.of(List.of(1, 2), List.of(3, 4));
        List<Integer> flatList = twoDimList.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        System.out.println(flatList);
    }
}
