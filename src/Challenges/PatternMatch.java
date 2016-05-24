package Challenges;
import java.util.*;

/**
 * Given an input catdogcatdogmonkeycatdogcatdog and pattern XXZXX, check if input matches the pattern.
 * Created by wentaod on 5/18/16.
 */
public class PatternMatch {
    String input;
    String pattern;
    Map<Character, String> map;

    private boolean matches(int idexInput, int idxPattern) {
        if (idexInput == input.length() && idxPattern == pattern.length())
            return true;
        if (idexInput >= input.length() || idxPattern >= pattern.length())
            return false;

        String macro = map.get(pattern.charAt(idxPattern));
        if (macro != null) { // that pattern is already visited
            for (int i=0; i<macro.length(); i++, idexInput++) {
                if (idexInput == input.length()) // input consumed before macro
                    return false;
                if (input.charAt(idexInput) != macro.charAt(i))
                    return false;
            }
            return matches(idexInput, idxPattern + 1);
        } else { // a new pattern
            for (int len = 2; len <= input.length() - idexInput; len++) {
                String ptn = input.substring(idexInput, idexInput + len);
                map.put(pattern.charAt(idxPattern), ptn);
                if (matches(idexInput + len, idxPattern + 1))
                    return true;
                map.remove(pattern.charAt(idxPattern));
            }
            return false;
        }
    }

    public boolean matches(String input, String pattern) {
        if (input==null || pattern==null || input.length()<2 || pattern.length()<1)
            return false;
        this.input = input;
        this.pattern = pattern;
        this.map = new HashMap<>();
        return matches(0, 0);
    }

    public static void main(String args[]) {
        String pattern = "XYXYZXYXY";
        String input = "catdogcatdogmonkeycatdogcatdog";
        PatternMatch solution = new PatternMatch();
        System.out.println(solution.matches(input, pattern));
    }
}
