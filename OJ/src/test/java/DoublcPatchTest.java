import org.junit.Test;

public class DoublcPatchTest {
    @Test
    public void test1() {
        Xb xb = new Xb();
        Aa aa = new Aa();
        Aa ab = new Ab();
        xb.f(aa);
        xb.f(ab);
    }
}

class Xa{
    void f(Aa arg) {
        System.out.print("Xa");
        arg.f();
    }
}

class Xb extends Xa{
    void f(Ab arg) {
        System.out.print("Xb");
        arg.f();
    }
}

class Aa {
    void f() {
        System.out.println("Aa");
    }
}
class Ab extends Aa{
    void f() {
        System.out.println("Ab");
    }
}
