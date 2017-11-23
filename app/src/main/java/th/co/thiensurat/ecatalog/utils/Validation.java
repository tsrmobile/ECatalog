package th.co.thiensurat.ecatalog.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by teerayut.k on 10/20/2017.
 */

public class Validation {

    public static boolean validCellPhone(String number) {
        String MobilePattern = "[0-9]{10}";
        if (number.matches(MobilePattern)) {
            return true;
        } else {
            return false;
        }
        //return android.util.Patterns.PHONE.matcher(number).matches();
    }

    public static boolean checkDigit(String id) {
        String checkbitStr = id.substring(id.length() - 1);
        int sumValue = 0;
        for (int i = 0; i < id.length()-1; i++) {
            sumValue += Integer.parseInt(String.valueOf(id.charAt(i))) * (13 - i);
            System.out.println("Check " + id.charAt(i) + "x" + i + " = " + Integer.parseInt(String.valueOf(id.charAt(i))) * (13 - i));
        }
        int v = 11 - (sumValue % 11);
        return id.charAt(12) - '0' == (v % 10);
        /*long idcard = Long.parseLong(id);
        long base = 100000000000l;
        int basenow;
        int sum = 0;
        for(int i = 13; i > 1; i--) {
            basenow = (int) Math.floor(idcard / base);
            idcard = idcard - basenow * base;
            System.out.println("Check " + basenow + "x" + i + " = " + (basenow * i));
            sum += basenow * i;
            base = base / 10;
        }
        System.out.println("Check Sum is " + sum);
        int checkbit = (11 - (sum % 11)) % 10;
        System.out.println("Check bit is " + checkbit + "("+ checkbitStr +")");

        if (checkbitStr.equals(checkbit)) {
            return true;
        } else {
            return false;
        }*/
    }

    public static boolean character(String name) {
        char[] chars = name.toCharArray();
        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }

    private static Pattern pattern;
    private static Matcher matcher;
    public static boolean validateEmail(String email) {
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
