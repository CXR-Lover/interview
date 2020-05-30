package count;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("���������飬�ٻ�����");
        });
        for (int i = 1; i <= 7; i++) {
            final Integer tempInt = i;
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "\t �Ѽ���" + tempInt + "������");
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
