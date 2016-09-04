package ua.com.juja.sqlcmd.Help;

import java.util.Arrays;

/**
 * Created by Oleg on 6/26/2016.
 */
public class Slope {

    public static void main(String[] args) {

        int[] i = {1, 2, 3};

        Object[] uai = {2, 3, 1};
        System.out.println(Arrays.toString(sortArray(uai)));
    }

    public static Object[] sortArray(Object[] names) {

        Arrays.sort(names);
        return names;
    }

    public static String tripleTrouble(String one, String two, String three) {
        String result = "";
        for (int i = 0; i < one.length(); i++) {
            result += one.charAt(i);
            result += two.charAt(i);
            result += three.charAt(i);
        }
        return result;
    }

    public static String stringy(int size) {
        String result = "";
        String a = "1";
        String b = "0";
        for (int i = 0; i < size; i++) {
            if (i % 2 == 0) {
                result += a;
            } else {
                result += b;
            }
        }
        return result;
    }

    public static String toAlternativeString(String string) {
      /*  String result = "";
        for (Character c : string.toCharArray()) {
            if (Character.isUpperCase(c)) {
                result += Character.toLowerCase(c);
            } else if (Character.isLowerCase(c)) {
                result += Character.toUpperCase(c);
            }
            return result;
        }*/

        char[] result = new char[string.length()];
        for (int i = 0; i < result.length; i++) {
            char c = result[i];
            if (Character.isUpperCase(c)) {
                c = Character.toLowerCase(c);
            } else if (Character.isLowerCase(c)) {
                c = Character.toUpperCase(c);
            }
            result[i] = c;
        }
        return new String(result);
    }

    public static double TwoDecimalPlaces(double number) {
        double round = Math.round(number * 100);
        return round / 100;
    }

    public static String SubstractSum(int n) {
        int sum = 0;
        for (int f = n; f > 0; f /= 10) {
            int plus = f % 10;
            sum += plus;
        }
        int result = n - sum;
        if (result > 10) {
            return SubstractSum(result);
        }
        return String.valueOf(result);
    }

    public static String leo(final int oscar) {

        if (oscar == 88) {
            return "Leo finally won the oscar! Leo is happy";
        }
        if (oscar == 86) {
            return "Not even for Wolf of wallstreet?!";
        }
        if (oscar < 88) {
            return "When will you give Leo an Oscar?";
        }
        if (oscar > 88) {
            return "Leo got one already!";
        }
        return "";
    }


    public String slope(int[] points) {
        int a = points[0];
        int b = points[1];
        int c = points[2];
        int d = points[3];
        if (a == b) return "undefined";

        if (c - a == 0) return "undefined";

        int ans = (d - b) / (c - a);
        return String.valueOf(ans);

    }

    public static String convert(boolean b) {
        return String.valueOf(b);
    }
}
