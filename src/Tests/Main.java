package Tests;
import java.lang.String;
import java.util.*;

class A {
    public void foo() {System.out.println("A");}
}

class B extends A {
    public void foo() {System.out.println("B");}
    public void bar() {System.out.println("B: bar");}
}

class C extends B {
    public void foo() {System.out.println("C");}
}

public class Main {

    public static void main(String[] args) {
        A a = new C();
        if(a instanceof A)
            a.foo();
        if(a instanceof B) {
            ((B)a).bar();
        }
        if(a instanceof C) {
            ((C)a).bar();
        }

    }
}
