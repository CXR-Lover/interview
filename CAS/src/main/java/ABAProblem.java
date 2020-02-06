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

//���ABA���⣬����ԭ����������(������ABA����) + �汾��/ʱ����������ABA���⣩
public class ABAProblem {


    static AtomicReference<Integer> afInteger = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> asrInteger = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {
        //����ABA����
        new Thread(() -> {
            afInteger.compareAndSet(100, 101);//ABA���� 100 - 101 - 100
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
        //���ABA����
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            //T3��ȡ�汾��
            int stampt = asrInteger.getStamp();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //T3��ʼʵ��ABA����
            System.out.println(Thread.currentThread().getName() + "\t��ʼֵ value=" + asrInteger.getReference() + "\t stamp=" + asrInteger.getStamp());
            System.out.println(asrInteger.compareAndSet(100, 101, stampt, ++stampt));
            System.out.println(Thread.currentThread().getName() + "\t��һ���޸ĺ� value=" + asrInteger.getReference() + "\t stamp=" + asrInteger.getStamp());
            System.out.println(asrInteger.compareAndSet(101, 100, asrInteger.getStamp(), ++stampt));
            System.out.println(Thread.currentThread().getName() + "\t�ڶ����޸ĺ� value=" + asrInteger.getReference() + "\t stamp=" + asrInteger.getStamp());
        }, "T3").start();
        new Thread(() -> {
            //T4��ȡ�汾��
            int stampt = asrInteger.getStamp();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(asrInteger.compareAndSet(100, 2019, stampt, ++stampt));
            System.out.println(Thread.currentThread().getName() + "\t��һ���޸ĺ� value=" + asrInteger.getReference() + "\t stamp=" + asrInteger.getStamp());
        }, "T4").start();


    }


}
