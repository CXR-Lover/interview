import java.util.concurrent.TimeUnit;

class Test {
    public volatile int number = 0;

    public void plus() {
        this.number = 60;
    }
}

/**
 * ��֤volatile�Ŀɼ���
 * ����˵���� while�������main�߳���һֱѭ�����number��ֵ
 */
public class VolatileVisitableDemo {
    public static void main(String[] args) throws InterruptedException {
        Test test = new Test();
        System.out.println(Thread.currentThread().getName() + "\t ����");
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            test.plus();
        }, "�߳�1").start();
        System.out.println(Thread.currentThread().getName() + "\t number =" + test.number);

        while (test.number == 0) {

        }
        System.out.println(Thread.currentThread().getName() + "\t number =" + test.number);
    }
}
