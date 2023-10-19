package innerClass;

//匿名内部类
public class Outer4 {
    private void test(final int i) { //匿名类使用的形参，声明为final
        new Service() {
            @Override
            public void method() { //匿名内部类不能定义静态成员或方法
                for (int j = 0; j < i; j++) {
                    System.out.println("匿名内部类");
                }
            }
        }.method();
    }

    public static void main(String[] args) {
        Outer4 outer4 = new Outer4();
        outer4.test(5);
    }
}

interface Service { //匿名内部类必须继承一个抽象类或者实现一个接口
    void method();
}