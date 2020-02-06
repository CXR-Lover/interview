import java.util.concurrent.TimeUnit;

class Test {
    //public int number = 0 ;
    public volatile int number = 0;

    public void plus() {
        this.number = 60;
    }
}

public class VolatileVisitableDemo {
    public static void main(String[] args) {
        Test t = new Test();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t Thread start");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t.plus();
            System.out.println(Thread.currentThread().getName() + "\t Thread end: value：" + t.number);
        }, "T").start();

        while (t.number == 0) {
            //为什么加上System.out.println 会导致不加volatile也可以是的number变量可见性？
            //因为println里面有synchronized 关键字，这个同步会导致CPU分出时间去保证内存的可见性。所以。。。。。。。。
            //System.out.println("123");
        }

        System.out.println(Thread.currentThread().getName() + " \t mission is over ,value=" + t.number);
    }
}
