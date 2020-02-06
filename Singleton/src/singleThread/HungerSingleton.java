package singleThread;

import com.sun.org.glassfish.gmbal.Description;

@Description("����ģʽ")
public class HungerSingleton {

    private static HungerSingleton hungerInstance = new HungerSingleton();

    private HungerSingleton() {
        System.out.println(Thread.currentThread().getName() + "\t �Ҵ�����һ���µĶ���");
    }

    public static HungerSingleton getHungerInstance() {
        return HungerSingleton.hungerInstance;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        for (int i = 1; i <= 2000; i++) {
            new Thread(() -> {
                HungerSingleton.getHungerInstance();
            }, String.valueOf(i)).start();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("������ " + (endTime - startTime) + " ����");
    }
}
