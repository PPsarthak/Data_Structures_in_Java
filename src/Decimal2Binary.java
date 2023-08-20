public class Decimal2Binary {
    static String bin_num = "";
    static void deci_to_bin(int x){
        if (x <= 1)
            bin_num += (char)(x + '0');
        else {
            deci_to_bin(x / 2);
            if(x%2 != 0) bin_num += '1';
            else bin_num += '0';
        }
    }


    public static void main(String[] args) {
        deci_to_bin(4);
        System.out.println(bin_num);
    }
}
