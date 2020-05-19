package converter;

import javax.swing.*;
import javax.xml.stream.events.Characters;
import java.util.Scanner;

public class Main {
    public static int lastDigit(int n) {
        return n % 8;
    }

    public static String convert(int n, int radix) {
        String prefix = "";
        switch (radix) {
            case 2:
                prefix = "0b";
                break;
            case 8:
                prefix = "0";
                break;
            case 16:
                prefix = "0x";
                break;
        }
        return prefix + Long.toString(n, radix);
    }

    public static String convert(String n, int radixsource, int radixtarget) {
        String result;
        if (radixsource == 1 || radixtarget == 1) {
            int value;

            if (radixsource == 1) {
                value = n.length();
            } else {
                value = Integer.parseInt(n, radixsource);
            }
            if (radixtarget == 1) {
                result = "1".repeat(value);
            } else {
                result = Integer.toString(value, radixtarget);
            }
        } else {
            result = Integer.toString(Integer.parseInt(n, radixsource), radixtarget);
        }

        return result;

    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int radixsource = scanner.nextInt();
        String v = scanner.next();
        int radixtarget = scanner.nextInt();
        if (v.indexOf('.') == -1 ){

            System.out.println(convert(v,radixsource,radixtarget));
        }
        else {
            String[] n = v.split("\\.");
            String e = n[0];
            String frac = n[1];
            String  es = convert(e,radixsource,radixtarget);

            double decimal = convertToDecimal(n[1],radixsource);
            String fs = convertToRadix(decimal,radixtarget);

            System.out.println(es+fs);

        }

    }

    private static String  convertToRadix(double d, int radix) {
        int n = 0;
        String result = ".";
        while ( n < 5 && d > 0 ){
            double x = d * radix;
           int digit =  (int) x;
            result  += getSymbol(digit);
            d = x - (int) x ;
           n++;
        }
        return result;
    }

    private static String getSymbol(int digit) {
        if (digit < 10){
            return ""+digit;
        }
        else {
            return  ""+Character.toChars(digit + 'a' -10)[0];
        }
    }

    private static double convertToDecimal(String s, double radixsource) {
        double r = 0;
        if (radixsource == 10){
            return Double.parseDouble("."+s);
        }

        double power = 1 / radixsource;
        while(s.length() >0){
            String digit = s.substring(0,1);
            int x = getValue(digit);
            r  +=   x * power;
            power *= 1 / radixsource;
            s = s.substring(1,s.length());
        }

        return r;
    }

    private static int getValue(String digit) {
        try {
            return Integer.parseInt(digit);
        }
        catch (Exception e){
            return digit.codePointAt(0) - 'a' + 10;
        }
    }


}