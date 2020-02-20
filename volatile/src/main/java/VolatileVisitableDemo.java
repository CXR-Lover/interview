import java.util.concurrent.TimeUnit;

class Test {
    public volatile int number = 0;

    public void plus() {
        this.number = 60;
    }
}

/**
 * 验证volatile的可见性
 * 代码说明： while代码块在main线程中一直循环监控number的值
 */
public class VolatileVisitableDemo {
    public static void main(String[] args) throws InterruptedException {
        Test test = new Test();
        System.out.println(Thread.currentThread().getName() + "\t 启动");
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            test.plus();
        }, "线程1").start();
        System.out.println(Thread.currentThread().getName() + "\t number =" + test.number);

        while (test.number == 0) {

        }
        System.out.println(Thread.currentThread().getName() + "\t number =" + test.number);
    }
}
