package Tests;
import java.util.*;

/**
 * Created by wentaod on 4/1/16.
 */
public class SortByFrequency {

    public static void main(String args[]) {
        final Map<String, Integer> freq = new HashMap<>();
        freq.put("Samsung", 10);
        freq.put("Apple", 15);
        freq.put("HTC", 5);
        freq.put("LG", 5);

        List<String> brands = new ArrayList<>(freq.keySet());
        Collections.sort(brands, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return freq.get(o2) - freq.get(o1);
            }
        });

        for(String brand : brands)
            System.out.println(brand);
    }
}
