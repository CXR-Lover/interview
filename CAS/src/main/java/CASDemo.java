import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {
    //ԭ����ĵײ�ʵ��ԭ��CAS��compare and swap �Ƚϲ������� �ײ���� ʹ�������� do���� while  ѭ��
    //ԭ�����������jdk��װ��AtomicInteger,AtomicBoolean�� �� ����Ա�Զ����AtomicReference<>
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
