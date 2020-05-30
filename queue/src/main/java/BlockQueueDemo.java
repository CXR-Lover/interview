import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.*;

public class BlockQueueDemo {

    /**
     * ������������Ѿ����ˣ���ô����add�����㱨illegalStateException��queue full�쳣
     * �����������Ϊ�գ�����remove�ᱨNoSuchElementException�쳣
     * element()�᷵�ض����ж��׵�ֵ
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
     * �������е�offer  �� poll ������
     * --�������������ˣ�������������offer���ݻ���ʾfalse
     * --����������Ϊ�գ���Ӷ�����poll���ݻ���ʾnull
     * peek�������ص�ǰ���еĵ�һ��ֵ
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
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () -> System.out.println("���������Ѿ�����"));
        for (int i = 0; i < 11; i++) {
            final int j = i;
            new Thread(() -> {
                try {
                    blockingQueue.put("i");
                    System.out.println(Thread.currentThread().getName() + "��������ֵ" + j + "�ɹ�");
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
