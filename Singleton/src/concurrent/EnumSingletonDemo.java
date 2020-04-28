package concurrent;

enum EnumSingletonDemo {

    INSTANCE;

    public void EnumSingletonDemo() {
        System.out.println(Thread.currentThread().getName() + "\t �������µ��߳�");
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                EnumSingletonDemo.INSTANCE.EnumSingletonDemo();
            }, String.valueOf(i)).start();
        }
    }
}
