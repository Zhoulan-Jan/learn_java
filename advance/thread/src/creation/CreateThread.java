package creation;

public class CreateThread {
     private static class MyThread extends Thread {
         @Override
         public void run() {
             for (int i = 0; i < 10; i++) {
                 System.out.println(i);
             }
         }
     }

     private static class MyRunnable implements Runnable {
         @Override
         public void run() {
             for (int i = 0; i < 10; i++) {
                 System.out.println(i);
             }
         }
     }

    public static void main(String[] args) throws InterruptedException {
        Thread a = new MyThread();
        a.start();
        a.join(); //1.主线程放弃CPU 2.主线程阻塞在这里，直到a线程执行结束，
        System.out.println("a 一定结束");

        Thread b = new Thread(new MyRunnable());
        b.start();
        b.join();
        System.out.println("b 一定结束");

        Thread c = new Thread(new MyThread());
        c.start();
        c.join();
        System.out.println("c 一定结束");
    }

    public static void UseAnonymous() {
         //使用匿名类创建子类对象
        // == 直接创建线程对象
        Thread a = new Thread() {
            @Override
            public void run() {
                //线程要执行的内容
            }
        };

        //使用匿名类创建 Runnable 子类对象
        // == 先创建目标对象，再创建线程对象
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                //线程要执行的内容
            }
        });

        //使用lambda表达式创建 Runnable 子类对象
        Thread c = new Thread(() -> {
            //线程要执行的内容
        });
    }
}
