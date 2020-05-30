package count;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("集齐了龙珠，召唤神龙");
        });
        for (int i = 1; i <= 7; i++) {
            final Integer tempInt = i;
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "\t 搜集到" + tempInt + "颗龙珠");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
