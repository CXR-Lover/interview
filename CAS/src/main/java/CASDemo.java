import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {
    //原子类的底层实现原理，CAS（compare and swap 比较并交换） 底层代码 使用自旋锁 do｛｝ while  循环
    //原子类包括本身jdk封装的AtomicInteger,AtomicBoolean等 和 程序员自定义的AtomicReference<>
    static AtomicInteger AtomicInteger = new AtomicInteger(0);
    static int i = 0;

    public static void main(String[] args) {
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    atomicPlus();
                }
            }, String.valueOf(i)).start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(i);
        System.out.println(AtomicInteger);
    }

    public static void plus() {
        i = i + 1;
    }


    public static void atomicPlus() {
        AtomicInteger.getAndIncrement();
    }


}
