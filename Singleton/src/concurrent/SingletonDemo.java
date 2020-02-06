package concurrent;

import com.sun.org.glassfish.gmbal.Description;

@Description("�߲�����̵���������ģʽ")
public class SingletonDemo {
    //����ʹ��volatile���Σ����Ա�֤�ڸ߲�������µ�һ���ԡ����ұ�ָ֤����š����ǲ���֤ԭ����
    private static SingletonDemo singletonDemo = null;

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName() + " \t ������һ���µĶ���");
    }

    public static SingletonDemo getInstance() {
        //˫�˼�������
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
