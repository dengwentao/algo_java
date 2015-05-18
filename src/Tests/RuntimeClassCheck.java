package Tests;

/**
 * Created by WentaoD on 5/14/15.
 */
public class RuntimeClassCheck {
  class Base {
    Base(){};
    public String tell() {return "I'm base.";};
  }

  class DerivedA extends Base{
    DerivedA(){};
    public String tell() {return "I'm Derived A.";};
  }

  class DerivedB extends Base {
    DerivedB() {};
    public String tell() {return "I'm Derived B.";};
  }

  void test() {
    Base oA = new DerivedA();
    System.out.println(oA.getClass());
    Base oBase = new Base();
    System.out.println(oBase.getClass());
  }

  public static void main(String args[]) {
    RuntimeClassCheck check = new RuntimeClassCheck();
    check.test();
  }
}
