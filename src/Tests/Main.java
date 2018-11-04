package Tests;
import java.util.*;

public class Main {



    public static void main(String [] args) {
        int a[] = {-3, 11, -4, -6, 9, 3};
        int max = Integer.MIN_VALUE;
        int sum = 0;
        int maxElem = Integer.MIN_VALUE;
        for (int x : a) {
            if (x > maxElem)
                maxElem = x;
            sum += x;
            if (sum > max)
                max = sum;
            if (sum < 0)
                sum = 0;
        }
        if (maxElem < 0)
            System.out.println(maxElem);
        else
            System.out.println(max);
    }

}
