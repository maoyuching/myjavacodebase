import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AnyThingTest {

    @Test
    public void test1() {
        A a = new A();
        A ac = new Achild();
        Achild achild = new Achild();

        X x = new X();
        X xc = new XChild();
        XChild xChild = new XChild();

        a.f(x); // A X
        a.f(xc); // A X
        a.f(xChild); // A X

        ac.f(x); // Achild X
        ac.f(xc); // Achild X
        ac.f(xChild); // Achild X --

        achild.f(x); // Achild X
        achild.f(xc); // Achild X
        achild.f(xChild); // Achild Xchild
    }

    @Test
    public void test2() {
        List a = null;
        Assert.assertTrue(a == null);
    }
}

class A{
    public void f(X x) {
        System.out.println("A X");
    }
}

class  Achild extends A{
    public void f(X x) {
        System.out.println("Achild X");
    }

    public void f(XChild xChild) {
        System.out.println("Achild xChild");
    }
}

class X{
}

class XChild extends X{

}
