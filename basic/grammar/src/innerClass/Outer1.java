package innerClass;

//静态内部类
public class Outer1 {
    private static int radius = 1;

    private static void method() {
        System.out.println("static method");
    }

    static class StaticInner {
        public void visit() {
            System.out.println("visit outer static variable: " + radius);
            System.out.println("visit outer static method: ");
            method();
        }
    }

    public static void main(String[] args) {
        Outer1.StaticInner inner = new Outer1.StaticInner();
        inner.visit();
    }
}
