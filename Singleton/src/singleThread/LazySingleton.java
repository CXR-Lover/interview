package singleThread;

import com.sun.org.glassfish.gmbal.Description;

@Description("多线程不安全的懒汉模式")
public class LazySingleton {
    //懒汉模式、多线程的情况下不安全,可能创建多个对象
    private static volatile LazySingleton lazySingleton = null;

    private LazySingleton() {
        System.out.println(Thread.currentThread().getName() + "\t 我创建了一个新的对象");
    }
    //有两个线程，线程1执行到判断为空，然后让出资源，让线程B执行，线程B也判断为空，然后实例化了，然后再释放资源，
    // 轮到线程1执行，线程1从刚才停止的地方继续，因为已经判断为空了，所以线程1也实例化了一个对象。
    // 因为CPU的调用存在随机性，故而存在这种可能，故而该方式线程不安全

    /**
     * 多线程下不安全的单例
     *
     * @return
     */
    public static LazySingleton getInstance() {
        if (lazySingleton == null) {
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }

    /**
     * 多线程下安全的单例，因为synchronized ，这个方法变成串行，导致效率变慢
     *
     * @return
     */
    public static synchronized LazySingleton getInstance2() {
        if (lazySingleton == null) {
            lazySingleton = new LazySingleton();
        } else {
            System.out.println(Thread.currentThread().getName() + "\t 已经存在一个对象，不能再创建对象");
        }
        return lazySingleton;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        for (int i = 1; i <= 2000; i++) {
            new Thread(() -> {
                LazySingleton.getInstance();
            }, String.valueOf(i)).start();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("花费了 " + (endTime - startTime) + " 毫秒");
    }
}
