package com.company;

/**
 * Created by wedeng on 2/4/15.
 */

class Outter {
    String msg = "Outter class object.";
    String title = "Outter";
    static String state = "static attr";

    public void display() {
        System.out.println(msg);
    }

    class Inner {
        String msg = "Inner class object.";
        public void display() {
            System.out.println(msg);
            System.out.println("Now to access outter class: " + title);
        }
    }

    static class StaticNested {
        public void display() {
            System.out.println(state);
        }
    }
}

public class InnerClassTest {
    public static void main(String[] args) {
        Outter out = new Outter();
        out.display();
        Outter.Inner in = out.new Inner();
        in.display();

        Outter.StaticNested stat= new Outter.StaticNested();
        stat.display();
    }
}
