import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class SpinLockDemo {

    AtomicReference<Thread> threadAtomicReference = new AtomicReference<>();

    public void lock() {
        Thread thread = Thread.currentThread();
        while (!threadAtomicReference.compareAndSet(null, thread)) {
            System.out.println(thread.getName() + "\n���ڻ�ȡ����");
        }
        System.out.println(thread.getName() + "\n�һ�ȡ������");
    }

    public void unLock() {
        Thread thread = Thread.currentThread();
        threadAtomicReference.compareAndSet(thread, null);
        System.out.println(thread.getName() + "\n �Ұ����ͷŵ���");
    }

    public static void main(String[] args) throws InterruptedException {
        SpinLockDemo spinLock = new SpinLockDemo();
        new Thread(() -> {
            System.out.println("�߳�AA��ʼ��");
            spinLock.lock();
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLock.unLock();
        }, "AA").start();


        new Thread(() -> {
            System.out.println("�߳�BB��ʼ��");
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
