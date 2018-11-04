package Challenges;
import java.util.*;

/**
 * Longest Increasing Sequence (LIS)
 * Created by wentaod on 1/28/16.
 */
public class LongestIncreasingSequence {


    // return the length of LIS
    public static int LIS2(int[] array) {
        List<Integer> lis = new ArrayList<>();
        for(int i : array) {
            insert2(i, lis);
        }
        return lis.size();
    }

    static void insert2(int v, List<Integer> lis) {
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

    public static int LIS(int[] array) {
        // SortedSet serves as a sorted array. Its key is the min element to satisfy corresponding length.
        TreeSet<Integer> lis = new TreeSet<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for(int i : array) {
            insert(i, lis);
        }
        return lis.size();
    }

    static void insert(int v, TreeSet<Integer> lis) {
        Integer entry = lis.ceiling(v);
        if (entry == null) {
            lis.add(v);
        } else {
            lis.remove(entry);
            lis.add(v);
        }
    }

    public static void main(String args[]) {
        int[] array = {2, 1, 5, 3, 6, 4, 8, 9, 7};
        System.out.println(LIS(array));
    }
}
