package concurrent;

import com.sun.org.glassfish.gmbal.Description;

@Description("高并发编程的完美单例模式")
public class SingletonDemo {
    //对象使用volatile修饰，可以保证在高并发情况下的一致性、并且保证指令不重排、但是不保证原子性
    private static SingletonDemo singletonDemo = null;

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName() + " \t 创建了一个新的对象");
    }

    public static SingletonDemo getInstance() {
        //双端检锁机制
        if (singletonDemo == null) {
            synchronized (SingletonDemo.class) {
                if (singletonDemo == null) {
                    singletonDemo = new SingletonDemo();
                }
            }
        }
        return singletonDemo;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 20000; i++) {
            new Thread(() -> {
                SingletonDemo.getInstance();
            }, String.valueOf(i)).start();
        }
    }
}
