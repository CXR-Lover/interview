import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


//重复往主内存中刷新变量，导致值的丢失。
//解决方案：1、synchronized 2、lock 3、原子类
class Test2 {
    volatile int number = 0;
    AtomicInteger atomicInteger = new AtomicInteger();

    //这个不保证原子性
    public void plus1() {
        number++;
    }

    //解决方法1
    public synchronized void synchronizedPlus() {
        number++;
    }

    //解决方法2
    public void lockPlus() {
        Lock lock = new ReentrantLock();
        lock.lock();
        number++;
        lock.unlock();
    }

    //解决方法3
    public void atomicPlus1() {
        //Atomic的底层原理是CAS(compare and swap)自旋锁,不断的通过比较主物理内存中的值，来更新主物理内存中的值
        //缺点：占用较高的资源，只能用于1个共享变量，ABA问题
        atomicInteger.getAndIncrement();
    }
}

public class VolatileNotAtomicDemo {
    public static void main(String[] args) {
        for (int z = 0; z < 10; z++) {
            Test2 test2 = new Test2();
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
            System.out.println(Thread.currentThread().getName() + "线程  \t number = " + test2.number);
            System.out.println(Thread.currentThread().getName() + "线程  \t number = " + test2.atomicInteger);
        }

    }
}
