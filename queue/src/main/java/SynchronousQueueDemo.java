import org.junit.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueDemo {

    @Test
    public void testPut() throws InterruptedException {

    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "\t 放入了 1");
                blockingQueue.put("1");
                System.out.println(Thread.currentThread().getName() + "\t 放入了 2");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName() + "\t 放入了 3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();


        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(blockingQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(blockingQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}

