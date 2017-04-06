package Tests;
import java.util.*;

public class Main {

    public static boolean hasTwoSum(int[] array, int target) {
        Arrays.sort(array);
        for (int i = 0, j = array.length - 1; i < j; ) {
            int sum = array[i] + array[j];
            if (sum < target) {
                i ++;
            } else if (sum > target) {
                j --;
            } else {
                return true;
            }
        }
        return false;
    }

    private static boolean hasTwoSumTo42(int[] array) {
        if (array == null || array.length < 2) {
            return false;
        }
        return hasTwoSum(array, 42);
    }

    private static void test(boolean expected, int[] input) {
        if (expected == hasTwoSumTo42(input)) {
            System.out.println("Correct");
        } else {
            System.out.println("Wrong!");
        }
    }

    public static void main(String [] args) {
        int[] a = {21, 21};
        test(true, a);

        int[] b = null;
        test(false, b);

        int[] c = {};
        test(false, c);

        int[] d = {42};
        test(false, d);

        int[] e = {-2, 20, -22, 22};
        test(true, e);

        int[] f = {-2, 20, 42, 42};
        test(false, f);
    }

}
