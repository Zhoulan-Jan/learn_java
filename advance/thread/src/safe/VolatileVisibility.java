package safe;

import java.util.Scanner;

public class VolatileVisibility {
    public volatile static boolean flag = true;

    private static class MyRunnable implements Runnable{

        private int n;

        @Override
        public void run() {
            while (flag) {
                n++;
            }
            System.out.println(n);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        MyRunnable target = new MyRunnable();
        Thread t = new Thread(target);
        t.start();

        sc.nextLine();
        flag = false;
        Thread.sleep(5*1000);
        System.out.println(t.isAlive()); //有volatile 输出false
    }
}
