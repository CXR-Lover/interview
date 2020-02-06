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
            System.out.println(Thread.currentThread().getName() + "\t Thread end: value��" + t.number);
        }, "T").start();

        while (t.number == 0) {
            //Ϊʲô����System.out.println �ᵼ�²���volatileҲ�����ǵ�number�����ɼ��ԣ�
            //��Ϊprintln������synchronized �ؼ��֣����ͬ���ᵼ��CPU�ֳ�ʱ��ȥ��֤�ڴ�Ŀɼ��ԡ����ԡ���������������
            //System.out.println("123");
        }

        System.out.println(Thread.currentThread().getName() + " \t mission is over ,value=" + t.number);
    }
}
