import java.sql.Time;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


//�ظ������ڴ���ˢ�±���������ֵ�Ķ�ʧ��
//���������1��synchronized 2��lock 3��ԭ����
class Test2 {
    volatile int number = 0;
    AtomicInteger atomicInteger = new AtomicInteger();

    //�������֤ԭ����
    public void plus1() {
        number++;
    }

    //�������1
    public synchronized void synchronizedPlus() {
        number++;
    }

    //�������2
    public void lockPlus() {
        Lock lock = new ReentrantLock();
        lock.lock();
        number++;
        lock.unlock();
    }

    //�������3
    public void atomicPlus1() {
        //Atomic�ĵײ�ԭ����CAS(compare and swap)������,���ϵ�ͨ���Ƚ��������ڴ��е�ֵ���������������ڴ��е�ֵ
        //ȱ�㣺ռ�ýϸߵ���Դ��ֻ������1�����������ABA����
        atomicInteger.getAndIncrement();
    }
}

public class VolatileNotAtomicDemo {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Test2 test2 = new Test2();
        for (int z = 0; z < 10; z++) {
            for (int i = 1; i <= 20; i++) {
                new Thread(() -> {
                    for (int j = 0; j < 10000; j++) {
                        test2.plus1();
                        test2.atomicPlus1();
                    }
                }, String.valueOf(i)).start();
            }
            while (Thread.activeCount() > 2) {
                Thread.yield();
            }
            System.out.println(Thread.currentThread().getName() + "�߳�  \t number = " + test2.number);
            System.out.println(Thread.currentThread().getName() + "�߳�  \t atomicInteger = " + test2.atomicInteger);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + "����");
    }
}
