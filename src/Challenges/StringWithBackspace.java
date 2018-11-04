package Challenges;

/**
 * Given a sequence of key strokes containing displayable characters and backspace, we can finally get a screen output.
 * Given two sequence of key strokes, comparing to see if the screen outputs are equal.
 * Do this with constant space and linear time.
 * Created by wd186013 on 9/28/18.
 */
public class StringWithBackspace {
    static boolean equals(String s1, String s2) {
        int i = s1.length() - 1;
        int j = s2.length() - 1;
        while (i >= -1 && j >= -1) { // we should continue even if one string is consumed up
            i = findPreviousOutputIndex(s1, i);
            j = findPreviousOutputIndex(s2, j);
            if (i >= 0 && s1.charAt(i) == '^' || j >= 0 && s2.charAt(j) == '^')
                continue; // it's possible that we still end on a backspace
            if (i == -1 || j == -1) // could be a backspace on an already empty string
                break;
            if (s1.charAt(i) != s2.charAt(j))
                return false;
            i --;
            j --;
        };
        return i == j; // return true if both -1
    }

    // Give current index, return the index of previous output char
    // Return -1 to indicate backspace on an empty string
    static int findPreviousOutputIndex(String s, int index) {
        if (index == -1)
            return -1;

        if (s.charAt(index) != '^')
            return index;

        int count = 1; // count of backspaces, we only come here when facing a backspace
        index --;
        while (index >= 0 && count > 0) {
            if (s.charAt(index) == '^')
                count ++; // encounted another backspace
            else
                count --; // consumed one backspace
            index --; // always move index one step forward
        }
        return index; // all backspaces were consumed, now we are on a outputable char
    }

    public static void main(String args[]) {
        System.out.println(equals("abccc^^d", "abccc^c^^d"));
        System.out.println(equals("", "^^"));
        System.out.println(equals("a", "a^"));
        System.out.println(equals("a^", "a^^"));
        System.out.println(equals("ab", "ab^b"));
        System.out.println(equals("ab^^^^ccc^^d", "a^b^c^c^c^ccc^^d"));
    }
}
