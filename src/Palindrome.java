public class Palindrome {
    public static void main(String[] args) {
        System.out.println(isPalindrome("abdcba"));
        System.out.println(palindrome("abcdmcba"));
    }
    //using two pointers
    static boolean isPalindrome(String s){
        boolean ans = true;
        int start = 0, end = s.length()-1;
        while(start<=end){
            if(s.charAt(start)==s.charAt(end)){
                start++;
                end--;
            }
            else{
                ans = false;
                break;
            }
        }
        return ans;
    }
    //using StringBuilder
    static boolean palindrome(String s){
        boolean ans = false;
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        String s1 = sb.toString();
        if(s.equals(s1)) ans = true;
        return ans;
    }
}
