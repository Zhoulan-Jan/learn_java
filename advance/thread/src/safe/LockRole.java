package safe;

public class LockRole {
    private static long n = 0;
    private static long COUNT = 1_0000_0000L;
    static Object  利用这个引用指向的对象作为锁 = new Object();


    static class Add extends Thread {
        @Override
        public void run() {
            for (long i = 0; i < COUNT; i++) {
                synchronized (利用这个引用指向的对象作为锁) {
                    n++;
                }
            }
        }
    }

    static class Sub extends Thread {
        @Override
        public void run() {
            for (long i = 0; i < COUNT; i++) {
                synchronized (利用这个引用指向的对象作为锁) {
                    n--;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Add add = new Add();
        Sub sub = new Sub();
        add.start();
        sub.start();

        add.join();
        sub.join();

        System.out.println(n);
    }
}
