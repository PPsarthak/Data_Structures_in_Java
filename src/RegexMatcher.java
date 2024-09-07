import java.util.regex.Pattern;

public class RegexMatcher {
    public static boolean matchRegex(String input) {
        String regex = "^0*10*$";
        return Pattern.matches(regex, input);
    }

    public static void main(String[] args) {
        String input1 = "1000"; // Example input
        String input2 = "0100"; // Example input
        String input3 = "0010"; // Example input
        String input4 = "0110"; // Example input

        System.out.println("Input 1 matches regex: " + matchRegex(input1));
        System.out.println("Input 2 matches regex: " + matchRegex(input2));
        System.out.println("Input 2 matches regex: " + matchRegex(input3));
        System.out.println("Input 2 matches regex: " + matchRegex(input4));
    }
}
