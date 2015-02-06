package Tests;
import java.lang.String;

/**
 * Created by wedeng on 1/28/15.
 * Given a String, write a routine that converts the string to an long, without using the built in Java functions that would do this.
 * Describe what (if any) limitations the code has.
 */

/*
* Limitations:
*  - Only allows digits. When meeting non-digit, throw exception;
*  - Only allow number between Long.MIN_VALUE and Long.MAX_VALUE; otherwise throws;
*  - Disallow comma;
*/


class InvalidInputException extends Exception {
    public InvalidInputException(String msg) {
        super(msg);
    }
}

class OutOfRangeException extends Exception {
    public OutOfRangeException(String msg) {
        super(msg);
    }
}


public class String2Long {

    public static long StringToLong(String s) throws InvalidInputException, OutOfRangeException {
        /* code goes here to convert a string to a long */
        int i = 0;
        boolean neg = false;
        if(s.charAt(0) == '-') {
            neg = true;
            i++;
        }
        else if(s.charAt(0) == '+') {
            i++;
        }

        long result = 0L;
        for(; i<s.length(); i++) {
            char c = s.charAt(i);
            long l = c - '0';
            if(l > 9 || l < 0)
                throw new InvalidInputException("Not a digit: " + c);
            long tmp = result * 10 + l;
            if(tmp < result)
                throw new OutOfRangeException("Out of range.");
            else
                result = tmp;
        }
        return neg ? -result : result;
    }

    private static void Test(String s, long l) {
        long i = 0L;
        try {
            i = StringToLong(s);
        }
        catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            return;
        }
        catch (OutOfRangeException e) {
            System.out.println(e.getMessage());
            return;
        }

        if (i == l) {
            System.out.println(s + " converted successfully to " + l);
        }
        else {
            System.out.println("Failed to convert " + s + " to " + l);
        }
    }

    public static void main(String[] args) {
        Test("-0", 0L);
        Test("123", 123L);
        Test("+123", 123L);
        Test("-123", -123L);
        Test("12x3", 123L);
        Test("x123", 123L);
        Test("9223372036854775807", 9223372036854775807L);
        Test("-9223372036854775807", -9223372036854775807L);
        Test("9223372036854775808", 123L);
    }

}
