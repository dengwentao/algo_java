package Tests;

/**
 * Created by wedeng on 2/4/15.
 */

class Singleton {
    int v;
    private static Singleton instance;// = null;
    public static Singleton getInstance() {
        if(instance==null) {
            synchronized (Singleton.class) {
                if(instance==null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public void test() {
        System.out.println("Hello world!");
    }
}

public class SingletonTest {
    public static void main(String[] args) {
        Singleton sig = Singleton.getInstance();
        sig.test();
    }
}
