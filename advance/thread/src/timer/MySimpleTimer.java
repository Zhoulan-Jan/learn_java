package timer;

import java.util.concurrent.TimeUnit;

//定时器，10秒后打印一个happy
public class MySimpleTimer {
    interface SimpleTimerTask {
        void run();
    }

    //这个线程先 sleep delay时间后，再去执行任务
    static class Worker extends Thread {
        long delay;
        SimpleTimerTask task;

        Worker(SimpleTimerTask task, long delay) {
            this.task = task;
            this.delay = delay;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(delay);
                task.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void schedule(SimpleTimerTask task, long delay) {
        Worker worker = new Worker(task, delay);
        worker.start();
    }

    static class MyTask implements SimpleTimerTask {
        @Override
        public void run() {
            System.out.println("happy");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MySimpleTimer timer = new MySimpleTimer();
        MyTask task = new MyTask();
        timer.schedule(task, 10*1000);
        int i = 0;
        while (true) {
            System.out.println(i++);
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
