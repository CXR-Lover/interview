import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

class User {
    private String name;
    private int sex;

    public User(String name, int sex) {
        this.name = name;
        this.sex = sex;
    }
}

//解决ABA问题，采用原子引用类型(产生了ABA问题) + 版本号/时间戳（解决了ABA问题）
public class ABAProblem {


    static AtomicReference<Integer> afInteger = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> asrInteger = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {
        //产生ABA问题
        new Thread(() -> {
            afInteger.compareAndSet(100, 101);//ABA问题 100 - 101 - 100
            afInteger.compareAndSet(101, 100);
        }, "T1").start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(afInteger.compareAndSet(100, 2019) + "\t" + afInteger.get());
        }, "T2").start();
        //解决ABA问题
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            //T3获取版本号
            int stampt = asrInteger.getStamp();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //T3开始实验ABA问题
            System.out.println(Thread.currentThread().getName() + "\t初始值 value=" + asrInteger.getReference() + "\t stamp=" + asrInteger.getStamp());
            System.out.println(asrInteger.compareAndSet(100, 101, stampt, ++stampt));
            System.out.println(Thread.currentThread().getName() + "\t第一次修改后 value=" + asrInteger.getReference() + "\t stamp=" + asrInteger.getStamp());
            System.out.println(asrInteger.compareAndSet(101, 100, asrInteger.getStamp(), ++stampt));
            System.out.println(Thread.currentThread().getName() + "\t第二次修改后 value=" + asrInteger.getReference() + "\t stamp=" + asrInteger.getStamp());
        }, "T3").start();
        new Thread(() -> {
            //T4获取版本号
            int stampt = asrInteger.getStamp();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(asrInteger.compareAndSet(100, 2019, stampt, ++stampt));
            System.out.println(Thread.currentThread().getName() + "\t第一次修改后 value=" + asrInteger.getReference() + "\t stamp=" + asrInteger.getStamp());
        }, "T4").start();


    }


}
