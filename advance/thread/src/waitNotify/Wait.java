package waitNotify;

import java.util.Scanner;

public class Wait {
    static Object o = new Object();

    static class MyThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                System.out.println(i);

                if (i == 30) {
                    synchronized (o) {
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread t = new MyThread();
        t.start();

        Thread.sleep(2*1000);
        System.out.println("输入任意字符唤醒Print线程");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        synchronized (o) {
            o.notify();
        }
    }
}
