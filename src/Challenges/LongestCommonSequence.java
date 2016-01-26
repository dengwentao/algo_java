package Challenges;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Find all the LCS' of two strings.
 * Created by wentaod on 1/25/16.
 */
public class LongestCommonSequence {

    static class Lcs {
        Set<String> lcs;
        int length;

        public Lcs() {
            lcs = new HashSet<>();
            lcs.add("");
            length = 0;
        }

        public Lcs(Lcs l) {
            length = l.length;
            lcs = new HashSet<>(l.lcs);
        }

        public Lcs(Lcs l1, Lcs l2) {
            lcs = new HashSet<>();
            if(l1.length > l2.length) {
                length = l1.length;
                lcs.addAll(l1.lcs);
            }
            else if(l1.length < l2.length) {
                length = l2.length;
                lcs.addAll(l2.lcs);
            }
            else {
                length = l1.length;
                lcs.addAll(l1.lcs);
                lcs.addAll(l2.lcs);
            }
        }

        public void append(char x) {
            length++;
            Set<String> lcs2 = new HashSet<>();
            for(String sb : lcs)
                lcs2.add(sb + x);
            lcs = lcs2;
        }

        public int length() {
            return length;
        }
    }

    static Lcs LCS(String s1, String s2) {
        if(s1.length() > s2.length()) { // to make s1 shorter than s2
            String tmp = s2;
            s2 = s1;
            s1 = tmp;
        }

        char[] x = s1.toCharArray();
        char[] y = s2.toCharArray();

        List<Lcs> dp = new ArrayList<>();
        for(int j=0; j<=x.length; j++)
            dp.add(new Lcs());

        for(int i=1; i<=y.length; i++) {
            Lcs corner = dp.get(0);
            for(int j=1; j<=x.length; j++) {
                Lcs savedCorner = new Lcs(dp.get(j));

                if(x[j-1] == y[i-1]) {
                    Lcs lcs = new Lcs(corner);
                    lcs.append(x[j-1]);
                    dp.set(j, lcs);
                }
                else {
                    dp.set(j, new Lcs(dp.get(j-1), dp.get(j)));
                }

                corner = savedCorner;
            }
        }

        return dp.get(x.length);
    }

    static void print(Set<String> lsb) {
        for(String sb : lsb)
            System.out.print(sb.toString()+",  ");
        System.out.println();
    }

    public static void main(String args[]) {
        String s1 = "abcxyz1234";
        String s2 = "123xyz4abc";
        Lcs lcs = LCS(s1, s2);
        System.out.println("-------");
        for(String sb : lcs.lcs){
            System.out.println(sb.toString());
        }
    }
}
