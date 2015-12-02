package Tests;
import java.lang.String;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        // index in BITree[] is 1 more than the index in arr[]
        int index = 8;
        index = index + 1;

        // Traverse all ancestors and add 'val'
        while (index <= 12)
        {
            System.out.println(index);
            // Update index to that of parent
            index += index & (-index);
        }
    }
}
