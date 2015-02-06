package Tests;
import java.util.*;
/**
 * Created by wedeng on 2/2/15.
 */
public class Test {
    public static void main(String[] args) {
        Stack<Character> s = new Stack<Character>();
        Queue<Character> q = new LinkedList<Character>();
        LinkedList<Character> l = new LinkedList<Character>();
        ArrayList<Character> a = new ArrayList<Character>();


        String str = "Hello world!";

        for(char c : str.toCharArray()) {
            q.offer(c);
        }

        while(!q.isEmpty()) {
            Character c = q.poll();
            System.out.print(c);
            s.push(c);
        }

        while(!s.empty()) {
            char c = s.pop();
            System.out.print(c);
            a.add(c);
        }

        reverse(a);
        for(int i=0; i<a.size(); i++) {
            char c = a.get(i);
            System.out.print(c);
            l.add(c);
        }

        ListIterator<Character> it = l.listIterator(l.size());
        while(it.hasPrevious()) {
            char c = it.previous();
            System.out.print(c);
        }

    }

    static private void reverse(ArrayList<Character> a) {
        int i = 0;
        int j = a.size()-1;
        while(i < j) {
            Character c = a.get(i);
            a.set(i, a.get(j));
            a.set(j, c);
            i++;
            j--;
        }
    }
}
