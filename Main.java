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
        try {
        int radixsource = scanner.nextInt();
        String v = scanner.next();
        int radixtarget = scanner.nextInt();
        if (radixsource <1 || radixtarget <1 || radixsource>36 || radixtarget>36){
            throw new Exception();
        }
        String r;

            if (v.indexOf('.') == -1) {
                r = convert(v, radixsource, radixtarget);
            } else {
                r = convertFraction(v, radixsource, radixtarget);
            }
            System.out.println(r);
        }
        catch (Exception a){
            System.out.println("error");
        }
    }

    private static String convertFraction(String v, int radixsource, int radixtarget) throws Exception {
        String[] n = v.split("\\.");
        String e = n[0];
        String frac = n[1];

            String es = convert(e, radixsource, radixtarget);

            double decimal = convertToDecimal(n[1], radixsource);
            String fs = convertToRadix(decimal, radixtarget);

            return es + fs;



    }


    private static String  convertToRadix(double d, int radix) throws Exception{
        int n = 0;
        String result = ".";
        while ( n < 5 && d > 0 ){
            double x = d * radix;
           int digit =  (int) x;
           if (digit > radix){
               throw new Exception();
           }
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

    private static double convertToDecimal(String s, double radixsource) throws Exception {
        double r = 0;
        if (radixsource == 10){
            return Double.parseDouble("."+s);
        }

        double power = 1 / radixsource;
        while(s.length() >0){
            String digit = s.substring(0,1);
            if (digit.codePointAt(0)  - 'a' + 10 > radixsource ){
                throw new Exception();
            }

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