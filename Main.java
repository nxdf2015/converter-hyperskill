package converter;

import java.util.Scanner;

public class Main {
    public static int lastDigit(int n){
        return n % 8 ;
    }
    public static void main(String[] args) {

        Scanner scanner =new Scanner(System.in);
        int n = scanner.nextInt();

        System.out.println(lastDigit(n));
    }
}
