package Tests;
import java.lang.String;
import java.util.*;


public class Main {

    static String firstRepeatedWord(String s) {
        if (s == null || s.length() < 2)
            return ""; // not found.

        Set<String> set = new HashSet<>();
        for (String part : s.split(" |\\t|:|;|-|\\.")) {
            if (part.length() != 0) {
                if (set.contains(part))
                    return part;
                set.add(part);
            }
        }

        return ""; // not found.
    }

    public static void main(String[] args) {
        System.out.println(firstRepeatedWord("ee..ee"));
    }
}
