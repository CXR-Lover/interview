package singleThread;

import com.sun.org.glassfish.gmbal.Description;

@Description("���̲߳���ȫ������ģʽ")
public class LazySingleton {
    //����ģʽ�����̵߳�����²���ȫ,���ܴ����������
    private static volatile LazySingleton lazySingleton = null;

    private LazySingleton() {
        System.out.println(Thread.currentThread().getName() + "\t �Ҵ�����һ���µĶ���");
    }
    //�������̣߳��߳�1ִ�е��ж�Ϊ�գ�Ȼ���ó���Դ�����߳�Bִ�У��߳�BҲ�ж�Ϊ�գ�Ȼ��ʵ�����ˣ�Ȼ�����ͷ���Դ��
    // �ֵ��߳�1ִ�У��߳�1�Ӹղ�ֹͣ�ĵط���������Ϊ�Ѿ��ж�Ϊ���ˣ������߳�1Ҳʵ������һ������
    // ��ΪCPU�ĵ��ô�������ԣ��ʶ��������ֿ��ܣ��ʶ��÷�ʽ�̲߳���ȫ

    /**
     * ���߳��²���ȫ�ĵ���
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
     * ���߳��°�ȫ�ĵ�������Ϊsynchronized �����������ɴ��У�����Ч�ʱ���
     *
     * @return
     */
    public static synchronized LazySingleton getInstance2() {
        if (lazySingleton == null) {
            lazySingleton = new LazySingleton();
        } else {
            System.out.println(Thread.currentThread().getName() + "\t �Ѿ�����һ�����󣬲����ٴ�������");
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
        System.out.println("������ " + (endTime - startTime) + " ����");
    }
}
