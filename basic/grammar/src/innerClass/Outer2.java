package innerClass;

//成员内部类
public class Outer2 {
    private static int radius = 1;
    private int count = 2;

    private static void method1() {
        System.out.println("static method");
    }

    private void method2() {
        System.out.println("method");
    }

    class Inner {
        public void visit() {
            System.out.println("visit outer static variable: " + radius);
            System.out.println("visit outer variable: " + count);

            System.out.println("visit outer static method: ");
            method1();
            System.out.println("visit outer method: ");
            method2();
        }
    }

    public static void main(String[] args) {
        Outer2 outer = new Outer2();
        Outer2.Inner inner = outer.new Inner();
        inner.visit();
    }
}
