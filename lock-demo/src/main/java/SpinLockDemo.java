import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class SpinLockDemo {

    AtomicReference<Thread> threadAtomicReference = new AtomicReference<>();

    public void lock() {
        Thread thread = Thread.currentThread();
        while (!threadAtomicReference.compareAndSet(null, thread)) {
            System.out.println(thread.getName() + "\n正在获取锁中");
        }
        System.out.println(thread.getName() + "\n我获取到锁了");
    }

    public void unLock() {
        Thread thread = Thread.currentThread();
        threadAtomicReference.compareAndSet(thread, null);
        System.out.println(thread.getName() + "\n 我把锁释放掉了");
    }

    public static void main(String[] args) throws InterruptedException {
        SpinLockDemo spinLock = new SpinLockDemo();
        new Thread(() -> {
            System.out.println("线程AA开始了");
            spinLock.lock();
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLock.unLock();
        }, "AA").start();


        new Thread(() -> {
            System.out.println("线程BB开始了");
            spinLock.lock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLock.unLock();
        }, "BB").start();
    }
}
