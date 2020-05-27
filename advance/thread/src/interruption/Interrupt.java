package interruption;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Interrupt {
    static class MyThread extends Thread {
//        @Override
//        public void run() {
//            for (int i = 0; i < 10;i++) {
//                //System.out.println(Thread.interrupted());
//                System.out.println(Thread.currentThread().isInterrupted());
//            }
//        }

        @Override
        public void run() {
            while (true) {
                //System.out.println(Thread.currentThread().isInterrupted()); //false->输入->准备终止->true
                System.out.println(Thread.interrupted()); //false->输入->准备终止->1个true->false
            }
        }
    }

    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start();
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        System.out.println("准备终止");
        t.interrupt();
    }
}
