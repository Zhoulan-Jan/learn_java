package innerClass;

//局部内部类
public class Outer3 {
    private int out_a = 1;
    private static int STATIC_B = 2;

    //定义在普通方法下的局部内部类
    public void testFuncClass() {
        int inner_c = 3;

        class Inner {
            private void visit() {
                System.out.println("外部变量： " + out_a);
                System.out.println("外部静态变量： " + STATIC_B);
                System.out.println("内部变量： " + inner_c);
            }
        }

        Inner inner = new Inner();
        inner.visit();
    }

    //定义在静态方法下的局部内部类
    public static void testStaticFuncClass() {
        int inner_d = 3;

        class Inner {
            private void visit() {
                //System.out.println("外部变量： " + out_a); //编译错误，定义在静态方法下的局部类不能访问外部的实例变量
                System.out.println("外部静态变量： " + STATIC_B);
                System.out.println("内部变量： " + inner_d); //inner_d为常量
            }
        }

        Inner inner = new Inner();
        inner.visit();
    }

    public static void main(String[] args) {
        Outer3 outer3 = new Outer3();
        outer3.testFuncClass();
        testStaticFuncClass();
    }
}
