package Challenges;

/**
 * Created by wentaod on 1/6/16.
 */
public class MajorityElement {

    static int majority(int[] a) {
        int count = 0;
        int maj = Integer.MIN_VALUE;
        for(int x : a) {
            if(count==0) {
                maj = x;
                count = 1;
            }
            else {
                count = maj == x ? count+1 : count-1;
            }
        }

        count = 0;
        for(int x : a)
            if(x == maj)
                count++;
        return count >= a.length/2.0 ? maj : Integer.MIN_VALUE;
    }

    static public void main(String args[]) {
        int[] a = {3, 3, 5, 3, 5, 3, 3, 5, 5, 5, 5};
        System.out.println(majority(a));
    }
}
