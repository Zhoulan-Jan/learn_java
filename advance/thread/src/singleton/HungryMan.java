package singleton;

public class HungryMan {
    //一开始就初始化
    private static HungryMan instance = new HungryMan();

    private HungryMan() {

    }
    public static HungryMan getInstance() {
        return instance;
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            HungryMan ins1 = HungryMan.getInstance();
            System.out.println(ins1);
        }
    }

    public static void main(String[] args) {
        //单线程环境下
//        singleton.HungryMan ins1 = singleton.HungryMan.getInstance();
//        singleton.HungryMan ins2 = singleton.HungryMan.getInstance();
//        singleton.HungryMan ins3 = singleton.HungryMan.getInstance();
//
//        System.out.println(ins1 == ins2); //true
//        System.out.println(ins2 == ins3); //true

        //多线程环境下也是同一个对象
        MyThread[] threads = new MyThread[20];
        for (int i = 0; i < 20; i++) {
            threads[i] = new MyThread();
        }

        for (int i = 0; i < 20; i++) {
            threads[i].start();
        }
    }
}
