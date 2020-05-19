package converter;

import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static int lastDigit(int n){
        return n % 8 ;
    }

    public static  String convert(int n,int radix){
        String prefix="";
        switch (radix){
            case 2:
                prefix="0b";
                break;
            case 8:
                prefix ="0";
                break;
            case 16:
                prefix = "0x";
                break;
        }
        return prefix+Long.toString(n,radix);
    }

    public static String convert(String n, int radixsource,int radixtarget){
        String result;
        if(radixsource == 1 || radixtarget==1){
            int value;

            if(radixsource == 1) {
                value = n.length();
            }
            else {
                value = Integer.parseInt(n,radixsource);
            }
            if (radixtarget == 1){
                result = "1".repeat(value);
            }
            else {
                result = Integer.toString(value,radixtarget);
            }
        }
        else {
            result = Integer.toString(Integer.parseInt(n,radixsource),radixtarget);
        }

        return result;

    }
    public static void main(String[] args) {

        Scanner scanner =new Scanner(System.in);

        int radixsource = scanner.nextInt();
        String  n = scanner.next();
        int radixtarget = scanner.nextInt();
        String result = convert(n,radixsource,radixtarget);
        System.out.println(result);
    }
}
