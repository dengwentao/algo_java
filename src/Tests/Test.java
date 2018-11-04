package Tests;
import java.util.regex.*;
/**
 * Created by wedeng on 2/2/15.
 */
public class Test {
    public static final int N = 3;

    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer();
        printPairs(sb, 0, 0);
    }

    static void printPairs(StringBuffer sb, int i, int j) {
        if (i == N && j == N) {
            System.out.println(sb.toString());
            return;
        } else if (i == N) {
            printPairs(sb.append(')'), i, j + 1);
            return;
        } else if (j == N) {
            return;
        } else if (i < j) {
            return;
        } else if (i == j) {
            printPairs(sb.append('('), i + 1, j);
        }
        else { //(i > j)
            StringBuffer sb2 = new StringBuffer(sb);
            printPairs(sb.append('('), i + 1, j);
            printPairs(sb2.append(')'), i, j + 1);
        }
    }
}
