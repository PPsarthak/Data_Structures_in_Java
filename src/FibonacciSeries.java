public class FibonacciSeries {
    public static void main(String[] args) {
        System.out.println(fibonacci(7));
        reverseNum(1842);
    }
    static int fibonacci(int n){
        if(n<2) return n;
        else{
            return fibonacci(n-1) + fibonacci(n-2);
        }
    }
    static void reverseNum(int n){
        if(n/10<1){
            System.out.println(n);
        }
        else{
            System.out.print(n%10);
            reverseNum(n/10);
        }
    }
}
