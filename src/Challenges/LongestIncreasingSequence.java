package Challenges;
import java.util.*;

/**
 * Longest Increasing Sequence (LIS)
 * Created by wentaod on 1/28/16.
 */
public class LongestIncreasingSequence {


    // return the length of LIS
    public static int LIS(int[] array) {
        List<Integer> lis = new ArrayList<>();
        for(int i : array) {
            insert(i, lis);
        }
        return lis.size();
    }

    static void insert(int v, List<Integer> lis) {
        int low = 0;
        int high = lis.size()-1;

        while(low <= high) {
            int mid = (high - low)/2 + low;
            if(lis.get(mid) < v) {
                low = mid + 1;
            }
            else if(lis.get(mid) > v) {
                if(low == high)
                    break; // to avoid infinite loop
                high = mid;
            }
            else {
                return; // dups are ignored.
            }
        }

        // insertion point is indicated by low
        if(lis.size() == low) {
            lis.add(v);
        }
        else
            lis.set(low, Math.min(v, lis.get(low)));
    }

    public static void main(String args[]) {
        int[] array = {2, 1, 5, 3, 6, 4, 8, 9, 7};
        System.out.println(LIS(array));
    }
}
