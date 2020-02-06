class Test3 {
    int a = 1;
    boolean flag = false;

    public void commandResort2() {
        a = 0;
        flag = true;
        if (flag) {
            a = a + 1;
            System.out.println("a=" + a);
        }
    }

}

public class VolatileCommandResort {

    public static void main(String[] args) {
        Test3 test3 = new Test3();
        for (int j = 1; j <= 200; j++) {
            new Thread(() -> {
                for (int k = 0; k < 10000; k++) {
                    test3.commandResort2();
                }
                while (!(test3.a == 2)) {
                    break;
                }
            }, "ThreadName").start();
        }

        System.out.println("11111111");
    }

}
