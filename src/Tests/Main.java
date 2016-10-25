package Tests;
import java.lang.String;
import java.util.*;
import java.io.*;


public class Main {

    List<Integer> tasks = new ArrayList<>();
    Map<Set<Integer>, Integer> dpSet = new HashMap<>();
    Map<Set<Integer>, Integer> dpTree = new HashMap<>();
    int divCost = 0;

    public static void main(String [] args) throws Exception{
        try {
            Main test = new Main();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(System.in));

            String str = "2,0";//in.readLine();
            String[] elems = str.split(",");
            if (elems.length == 0) {
                System.out.println("0");
                return;
            }
            Set<Integer> indexes = new HashSet<>();
            for (int i=0; i<elems.length; i++) {
                int c = Integer.valueOf(elems[i]);
                if (c == 0)
                    continue;
                test.tasks.add(c);
                indexes.add(i);
            }

            str = "3";//in.readLine();
            test.divCost = Integer.valueOf(str);

            int cst = test.costSet(indexes);
            System.out.println(cst);
        }  catch (NumberFormatException ex) {
            System.out.println(ex);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    // given a set of costs, return minimum cost
    int costSet(Set<Integer> indexes) {
        if (dpSet.get(indexes) != null)
            return dpSet.get(indexes);

        if (indexes.size() == 0) {
            dpSet.put(indexes, 0);
            return 0;
        }

        if (indexes.size() == 1) {
            Integer c = tasks.get(indexes.iterator().next());
            dpSet.put(indexes, c);
            return c;
        }

        int min = Integer.MAX_VALUE;
        for (int i : indexes) {
            Set<Integer> clone = (Set)((HashSet)indexes).clone();
            clone.remove(i);
            int treeCost = tasks.get(i) + costTree(clone);
            if (treeCost < min)
                min = treeCost;
        }
        int full = costTree(indexes);
        if (full < min)
            min = full;
        min += divCost;

        System.out.println("indexes="+indexes + ", min="+min);
        dpSet.put(indexes, min);
        return min;
    }

    // arange the set of indexes into bi-tree in various ways, and return
    // the min cost.
    int costTree(Set<Integer> indexes) {
        if (dpTree.get(indexes) != null)
            return dpTree.get(indexes);

        if (indexes.size() == 0) {
            dpTree.put(indexes, 0);
            return 0;
        }

        if (indexes.size() == 1) {
            int c = tasks.get(indexes.iterator().next());
            dpTree.put(indexes, c);
            return c;
        }

        int min = Integer.MAX_VALUE;
        Set<Integer> left = (Set)((HashSet)indexes).clone();
        Set<Integer> right = new HashSet<>();
        Iterator<Integer> it = left.iterator();
        while (left.size() > 1) {
            int i = it.next();
            right.add(i);
            it.remove();
            int l = costSet(left);
            int r = costSet(right);
            int t = l > r ? l : r;
            if (t < min)
                min = t;
        }

        dpTree.put(indexes, min);
        return min;
    }
}
