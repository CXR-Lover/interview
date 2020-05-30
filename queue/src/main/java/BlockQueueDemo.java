import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.*;

public class BlockQueueDemo {

    /**
     * 如果阻塞队列已经满了，那么调用add方法汇报illegalStateException：queue full异常
     * 如果阻塞队列为空，调用remove会报NoSuchElementException异常
     * element()会返回队列中队首的值
     */
    @Test
    public void testAddAndRemove() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.add("1");
        blockingQueue.addAll(Arrays.asList("2", "3"));
        blockingQueue.remove("2");
        System.out.println(blockingQueue.element());
    }

    /**
     * 阻塞队列的offer  和 poll 方法：
     * --当阻塞队列满了，继续往队列中offer数据会提示false
     * --当阻塞队列为空，则从队列中poll数据会提示null
     * peek方法返回当前队列的第一个值
     */
    @Test
    public void testOfferAndPoll() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("1"));
        System.out.println(blockingQueue.offer("2"));
        System.out.println(blockingQueue.offer("1"));
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());

        System.out.println(blockingQueue.peek());
    }

    @Test
    public void testPutAndTake() throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(10);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () -> System.out.println("阻塞队列已经满了"));
        for (int i = 0; i < 11; i++) {
            final int j = i;
            new Thread(() -> {
                try {
                    blockingQueue.put("i");
                    System.out.println(Thread.currentThread().getName() + "向队列添加值" + j + "成功");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
        TimeUnit.SECONDS.sleep(2);
        blockingQueue.take();
    }
}
