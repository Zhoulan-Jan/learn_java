package properties;

public class ThreadProperties {
    public static void printFields() {
        Thread t = Thread.currentThread();  //当前线程
        System.out.println(t.getId()); //线程的唯一标识
        System.out.println(t.getName()); //线程的名称
        System.out.println(t.getState()); //线程的状态
        System.out.println(t.getPriority()); //线程的优先级
        System.out.println(t.isDaemon()); //是否为后台进程
                                          //JVM会在所有非后台线程结束后，结束运行
        System.out.println(t.isAlive()); //是否存活，只有 NEW/TERMINATED 返回false
        System.out.println(t.isInterrupted()); //是否被中断
        System.out.println("============");
    }
    public static void main(String[] args) {
        printFields();
        Thread t = new Thread() {
            @Override
            public void run() {
                printFields();
                try {
                    Thread.sleep(10000000); //主动放弃CPU，这段时间后重新抢回CPU
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }
}
